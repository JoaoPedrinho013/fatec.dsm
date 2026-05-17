"""
Controller — Usuario
Dados pessoais do usuário autenticado.
"""

from app.database.redis_db import get_redis
from app.models import usuario_model as UM
from app.models import livro_model as LM
from app.models import emprestimo_model as EM
import time
import json


def _r():
    return get_redis()


def listar_emprestimos(username):
    ids = _r().smembers(UM.emprestimos_key(username))
    resultado = []
    for livro_id in ids:
        livro_data = _r().hgetall(LM.key(livro_id))
        emp_data = _r().hgetall(EM.key(username, livro_id))
        if livro_data and emp_data:
            resultado.append({
                **livro_data,
                "devolucao_em": emp_data.get("devolucao_em"),
            })
    return resultado


def listar_favoritos(username):
    ids = _r().smembers(UM.favoritos_key(username))
    livros = []
    for livro_id in ids:
        data = _r().hgetall(LM.key(livro_id))
        if data:
            livros.append(data)
    return livros


def listar_espera(username):
    ids = _r().smembers(UM.espera_key(username))
    livros = []
    for livro_id in ids:
        data = _r().hgetall(LM.key(livro_id))
        if data:
            livros.append(data)
    return livros


def _parse_notif(raw):
    """Converte string bruta (JSON novo ou texto legado) para dict."""
    try:
        return json.loads(raw)
    except Exception:
        return {"mensagem": raw, "lida": False}


def listar_notificacoes(username):
    raws = _r().lrange(UM.notificacoes_key(username), 0, -1)
    result = []
    for i, raw in enumerate(raws):
        n = _parse_notif(raw)
        result.append({"index": i, "mensagem": n["mensagem"], "lida": n.get("lida", False)})
    return result


def marcar_notificacao_lida(username, index):
    raws = _r().lrange(UM.notificacoes_key(username), 0, -1)
    if index < 0 or index >= len(raws):
        return False
    n = _parse_notif(raws[index])
    n["lida"] = True
    _r().lset(UM.notificacoes_key(username), index, json.dumps(n, ensure_ascii=False))
    return True


def remover_notificacao(username, index):
    placeholder = "__REMOVIDO__"
    _r().lset(UM.notificacoes_key(username), index, placeholder)
    _r().lrem(UM.notificacoes_key(username), 1, placeholder)
    return True
