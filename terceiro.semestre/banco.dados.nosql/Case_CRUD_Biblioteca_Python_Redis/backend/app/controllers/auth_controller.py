"""
Controller — Autenticação
Lida com registro, login e gerenciamento de usuários.
"""

import base64
from app.database.redis_db import get_redis
from app.models import usuario_model as UM


def _r():
    return get_redis()


# ---------- helpers ----------

def _get_usuario(username):
    data = _r().hgetall(UM.key(username))
    if not data:
        return None
    return UM.Usuario.from_dict(data)


def _autenticar(username, password):
    data = _r().hgetall(UM.key(username))
    if not data:
        return None
    if data.get("password_hash") != UM.hash_password(password):
        return None
    return UM.Usuario.from_dict(data)


def parse_basic_auth(auth_header):
    """Extrai username e password de um header Authorization Basic."""
    if not auth_header or not auth_header.startswith("Basic "):
        return None, None
    try:
        decoded = base64.b64decode(auth_header[6:]).decode("utf-8")
        username, password = decoded.split(":", 1)
        return username, password
    except Exception:
        return None, None


# ---------- operações ----------

def criar_admin_padrao():
    """Cria o admin padrão se não existir (chamado no startup)."""
    if not _r().exists(UM.key("admin")):
        _r().hset(UM.key("admin"), mapping={
            "username": "admin",
            "password_hash": UM.hash_password("admin"),
            "cargo": UM.Usuario.CARGO_ADMIN,
        })


def registrar(username, password, cargo=None):
    if not username or not username.strip():
        return None, "Username é obrigatório"
    if not password or len(password) < 4:
        return None, "Senha deve ter ao menos 4 caracteres"
    if _r().exists(UM.key(username)):
        return None, "Username já existe"

    cargo = cargo or UM.Usuario.CARGO_USER
    _r().hset(UM.key(username), mapping={
        "username": username,
        "password_hash": UM.hash_password(password),
        "cargo": cargo,
    })
    return UM.Usuario(username, cargo).to_dict(), None


def login(auth_header):
    username, password = parse_basic_auth(auth_header)
    if not username:
        return None, "Credenciais inválidas"
    user = _autenticar(username, password)
    if not user:
        return None, "Username ou senha incorretos"
    return user.to_dict(), None


def listar_usuarios():
    pattern = f"{UM.key('*')}"
    chaves = _r().keys(pattern)
    usuarios = []
    for chave in chaves:
        # Filtra sub-chaves como :emprestimos, :favoritos etc.
        partes = chave.split(":")
        # Formato esperado: bib2:usuario:{username}  (3 partes)
        if len(partes) == 3:
            data = _r().hgetall(chave)
            if data and "username" in data:
                usuarios.append({"username": data["username"], "cargo": data.get("cargo", "User")})
    return usuarios


def deletar_usuario(username):
    if username == "admin":
        return False, "Não é possível deletar o admin"
    if not _r().exists(UM.key(username)):
        return False, "Usuário não encontrado"
    pipe = _r().pipeline()
    pipe.delete(UM.key(username))
    pipe.delete(UM.emprestimos_key(username))
    pipe.delete(UM.favoritos_key(username))
    pipe.delete(UM.espera_key(username))
    pipe.delete(UM.notificacoes_key(username))
    pipe.execute()
    return True, None


def requer_auth(auth_header):
    """Retorna (usuario_dict, erro). Uso: user, err = requer_auth(...)"""
    username, password = parse_basic_auth(auth_header)
    if not username:
        return None, "Autenticação necessária"
    user = _autenticar(username, password)
    if not user:
        return None, "Credenciais inválidas"
    return user.to_dict(), None


def requer_admin(auth_header):
    user, err = requer_auth(auth_header)
    if err:
        return None, err
    if user["cargo"] != UM.Usuario.CARGO_ADMIN:
        return None, "Acesso restrito a administradores"
    return user, None
