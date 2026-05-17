"""
Model — Usuario
Representa um usuário do sistema.
Chave Redis: bib2:usuario:{username}     (Hash)
Empréstimos: bib2:usuario:{username}:emprestimos  (Set de livro_ids)
Favoritos:   bib2:usuario:{username}:favoritos    (Set de livro_ids)
Espera:      bib2:usuario:{username}:espera       (Set de livro_ids)
Notifs:      bib2:usuario:{username}:notificacoes (List)
"""

import hashlib
from config import Config

P = Config.KEY_PREFIX


def key(username):
    return f"{P}:usuario:{username}"


def emprestimos_key(username):
    return f"{P}:usuario:{username}:emprestimos"


def favoritos_key(username):
    return f"{P}:usuario:{username}:favoritos"


def espera_key(username):
    return f"{P}:usuario:{username}:espera"


def notificacoes_key(username):
    return f"{P}:usuario:{username}:notificacoes"


def hash_password(password):
    return hashlib.sha256(password.encode()).hexdigest()


class Usuario:
    CARGO_ADMIN = "Admin"
    CARGO_USER = "User"

    def __init__(self, username, cargo=None):
        self.username = username
        self.cargo = cargo or self.CARGO_USER

    def to_dict(self):
        return {"username": self.username, "cargo": self.cargo}

    @classmethod
    def from_dict(cls, data):
        return cls(username=data.get("username"), cargo=data.get("cargo", cls.CARGO_USER))

    def is_admin(self):
        return self.cargo == self.CARGO_ADMIN
