"""
Model — Emprestimo
Chave Redis: bib2:emprestimo:{username}:{livro_id}   (Hash)
Vencimentos: bib2:emprestimos_vencimento             (Sorted Set)
"""

import time
from config import Config

P = Config.KEY_PREFIX
DURACAO_EMPRESTIMO = 60 * 5  # 5 minutos em segundos


def key(username, livro_id):
    return f"{P}:emprestimo:{username}:{livro_id}"


def vencimento_key():
    return f"{P}:emprestimos_vencimento"


class Emprestimo:
    def __init__(self, username, livro_id, devolucao_em=None):
        self.username = username
        self.livro_id = str(livro_id)
        self.devolucao_em = devolucao_em or int(time.time()) + DURACAO_EMPRESTIMO

    def to_dict(self):
        return {
            "username": self.username,
            "livro_id": self.livro_id,
            "devolucao_em": str(self.devolucao_em),
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            username=data.get("username"),
            livro_id=data.get("livro_id"),
            devolucao_em=int(data.get("devolucao_em", 0)),
        )
