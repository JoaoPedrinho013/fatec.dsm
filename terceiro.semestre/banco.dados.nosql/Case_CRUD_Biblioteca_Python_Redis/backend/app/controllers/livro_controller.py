"""
Controller — Livro
CRUD completo + empréstimo, devolução, favoritos, lista de espera e auto-retorno.
"""

import time
import json
from app.database.redis_db import get_redis
from app.models import livro_model as LM
from app.models import usuario_model as UM
from app.models import emprestimo_model as EM

LIMITE_EMPRESTIMOS = 3


def _r():
    return get_redis()


# ---------- CRUD ----------

def criar_livro(data):
    livro = LM.Livro.from_dict(data)
    errors = livro.validate()
    if errors:
        return None, errors

    novo_id = _r().incr(LM.counter_key())
    livro.id = str(novo_id)
    # Salva no Redis sem o campo 'categorias' (campo calculado, não armazenado)
    redis_data = {k: v for k, v in livro.to_dict().items() if k != "categorias"}
    _r().hset(LM.key(novo_id), mapping=redis_data)
    return livro.to_dict(), None


def listar_livros():
    chaves = _r().keys(f"{LM.P}:livro:*")
    livros = []
    for chave in chaves:
        partes = chave.split(":")
        if len(partes) == 3:
            data = _r().hgetall(chave)
            if data:
                l = LM.Livro.from_dict(data)
                livros.append(l.to_dict())
    livros.sort(key=lambda x: int(x.get("id", 0)))
    return livros


def buscar_livro(livro_id):
    data = _r().hgetall(LM.key(livro_id))
    if not data:
        return None, "Livro não encontrado"
    l = LM.Livro.from_dict(data)
    return l.to_dict(), None


def atualizar_livro(livro_id, data):
    if not _r().exists(LM.key(livro_id)):
        return None, "Livro não encontrado"

    atual = _r().hgetall(LM.key(livro_id))
    # Atualiza campos enviados
    for k, v in data.items():
        if v is not None and v != "" and k != "categorias":
            atual[k] = v
    atual["id"] = str(livro_id)

    livro = LM.Livro.from_dict(atual)
    errors = livro.validate()
    if errors:
        return None, errors

    redis_data = {k: v for k, v in livro.to_dict().items() if k != "categorias"}
    _r().hset(LM.key(livro_id), mapping=redis_data)
    return livro.to_dict(), None


def deletar_livro(livro_id):
    if not _r().exists(LM.key(livro_id)):
        return False, "Livro não encontrado"
    pipe = _r().pipeline()
    pipe.delete(LM.key(livro_id))
    pipe.delete(LM.espera_key(livro_id))
    pipe.execute()
    return True, None


# ---------- Empréstimos ----------

def emprestar(livro_id, username):
    livro_data = _r().hgetall(LM.key(livro_id))
    if not livro_data:
        return False, "Livro não encontrado"

    qtd = int(livro_data.get("quantidade", 0))
    if qtd <= 0:
        return False, "Livro indisponível no momento"

    emprestimos_ativos = _r().scard(UM.emprestimos_key(username))
    if emprestimos_ativos >= LIMITE_EMPRESTIMOS:
        return False, f"Limite de {LIMITE_EMPRESTIMOS} empréstimos simultâneos atingido"

    if _r().sismember(UM.emprestimos_key(username), str(livro_id)):
        return False, "Você já possui este livro emprestado"

    emprestimo = EM.Emprestimo(username=username, livro_id=livro_id)
    pipe = _r().pipeline()
    pipe.hset(EM.key(username, livro_id), mapping=emprestimo.to_dict())
    pipe.sadd(UM.emprestimos_key(username), str(livro_id))
    nova_qtd = qtd - 1
    pipe.hset(LM.key(livro_id), "quantidade", nova_qtd)
    if nova_qtd == 0:
        pipe.hset(LM.key(livro_id), "status", LM.Livro.STATUS_EMPRESTADO)
    pipe.zadd(EM.vencimento_key(), {f"{username}:{livro_id}": emprestimo.devolucao_em})
    pipe.execute()
    return True, None


def devolver(livro_id, username):
    if not _r().sismember(UM.emprestimos_key(username), str(livro_id)):
        return False, "Você não possui este livro emprestado"

    livro_data = _r().hgetall(LM.key(livro_id))
    qtd = int(livro_data.get("quantidade", 0)) + 1

    pipe = _r().pipeline()
    pipe.delete(EM.key(username, livro_id))
    pipe.srem(UM.emprestimos_key(username), str(livro_id))
    pipe.hset(LM.key(livro_id), "quantidade", qtd)
    pipe.hset(LM.key(livro_id), "status", LM.Livro.STATUS_DISPONIVEL)
    pipe.zrem(EM.vencimento_key(), f"{username}:{livro_id}")
    pipe.execute()

    _notificar_espera(livro_id, livro_data.get("titulo", ""))
    return True, None


def _push_notif(username, mensagem):
    """Salva uma notificação como JSON com campo 'lida: false'."""
    payload = json.dumps({"mensagem": mensagem, "lida": False}, ensure_ascii=False)
    _r().lpush(UM.notificacoes_key(username), payload)


def _notificar_espera(livro_id, titulo):
    espera = _r().smembers(LM.espera_key(livro_id))
    if not espera:
        return
    primeiro = list(espera)[0]
    _push_notif(primeiro, f"O livro '{titulo}' está disponível para você!")
    _r().srem(LM.espera_key(livro_id), primeiro)
    _r().srem(UM.espera_key(primeiro), str(livro_id))


# ---------- Auto-retorno de empréstimos vencidos ----------

def verificar_emprestimos_vencidos():
    """
    Chamado pelo background thread a cada 30s.
    Verifica o sorted set de vencimentos e devolve automaticamente
    os livros cujo prazo expirou, notificando o usuário.
    """
    agora = int(time.time())
    vencidos = _r().zrangebyscore(EM.vencimento_key(), "-inf", agora)

    for membro in vencidos:
        try:
            username, livro_id = membro.rsplit(":", 1)
            livro_data = _r().hgetall(LM.key(livro_id))
            if not livro_data:
                _r().zrem(EM.vencimento_key(), membro)
                continue

            # Verifica se o empréstimo ainda existe
            if not _r().sismember(UM.emprestimos_key(username), str(livro_id)):
                _r().zrem(EM.vencimento_key(), membro)
                continue

            titulo = livro_data.get("titulo", f"Livro #{livro_id}")
            qtd = int(livro_data.get("quantidade", 0)) + 1

            pipe = _r().pipeline()
            pipe.delete(EM.key(username, livro_id))
            pipe.srem(UM.emprestimos_key(username), str(livro_id))
            pipe.hset(LM.key(livro_id), "quantidade", qtd)
            pipe.hset(LM.key(livro_id), "status", LM.Livro.STATUS_DISPONIVEL)
            pipe.zrem(EM.vencimento_key(), membro)
            pipe.execute()
            # Notificação para o usuário (fora do pipeline para usar _push_notif)
            _push_notif(username, f"⏰ Seu empréstimo de '{titulo}' expirou e foi devolvido automaticamente.")

            print(f"[Auto-retorno] Livro '{titulo}' devolvido por {username}")

            # Notifica quem está na lista de espera
            _notificar_espera(livro_id, titulo)

        except Exception as e:
            print(f"[Auto-retorno] Erro ao processar '{membro}': {e}")


# ---------- Favoritos ----------

def favoritar(livro_id, username):
    if not _r().exists(LM.key(livro_id)):
        return False, "Livro não encontrado"
    if _r().sismember(UM.favoritos_key(username), str(livro_id)):
        return False, "Livro já está nos favoritos"
    _r().sadd(UM.favoritos_key(username), str(livro_id))
    return True, None


def remover_favorito(livro_id, username):
    if not _r().sismember(UM.favoritos_key(username), str(livro_id)):
        return False, "Livro não está nos favoritos"
    _r().srem(UM.favoritos_key(username), str(livro_id))
    return True, None


# ---------- Lista de espera ----------

def entrar_espera(livro_id, username):
    if not _r().exists(LM.key(livro_id)):
        return False, "Livro não encontrado"
    if _r().sismember(UM.espera_key(username), str(livro_id)):
        return False, "Você já está na lista de espera deste livro"
    _r().sadd(LM.espera_key(livro_id), username)
    _r().sadd(UM.espera_key(username), str(livro_id))
    return True, None
