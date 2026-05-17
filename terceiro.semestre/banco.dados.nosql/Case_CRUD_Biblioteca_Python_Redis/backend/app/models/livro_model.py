"""
Model — Livro
Representa um livro na biblioteca digital.
Chave Redis: bib2:livro:{id}  (Hash)
Contador:    bib2:livro_id    (String/int)

Categorias: armazenadas como string separada por vírgula, ex: "Fantasia,Tecnologia"
"""

from config import Config

P = Config.KEY_PREFIX  # "bib2"

CATEGORIAS_VALIDAS = [
    "Arte", "Autoajuda", "Biografia", "Ciência", "Cinema",
    "Crônica", "Direito", "Economia", "Educação", "Esportes",
    "Fantasia", "Ficção", "Ficção Científica", "Filosofia",
    "Gastronomia", "História", "Horror", "Infantil", "Jovem Adulto",
    "Juvenil", "Literatura", "Medicina", "Mistério", "Música",
    "Outros", "Poesia", "Política", "Psicologia", "Quadrinhos",
    "Religião", "Romance", "Suspense", "Tecnologia", "Terror",
    "Thriller", "Viagem",
]


def key(livro_id):
    return f"{P}:livro:{livro_id}"


def counter_key():
    return f"{P}:livro_id"


def espera_key(livro_id):
    return f"{P}:livro:{livro_id}:espera"


class Livro:
    CAMPOS = ["id", "titulo", "autor", "categoria", "ano", "quantidade", "status", "sinopse"]
    STATUS_DISPONIVEL = "Disponivel"
    STATUS_EMPRESTADO = "Emprestado"

    def __init__(self, id, titulo, autor, categoria, ano, quantidade, status=None, sinopse=None):
        self.id = str(id)
        self.titulo = titulo
        self.autor = autor
        # categoria pode ser lista ou string separada por vírgula
        if isinstance(categoria, list):
            self.categoria = ",".join(c.strip() for c in categoria if c.strip())
        else:
            self.categoria = str(categoria or "")
        self.ano = str(ano)
        self.quantidade = str(quantidade)
        self.status = status or self.STATUS_DISPONIVEL
        self.sinopse = sinopse or ""

    def categorias_lista(self):
        """Retorna categorias como lista Python."""
        return [c.strip() for c in self.categoria.split(",") if c.strip()]

    def to_dict(self):
        return {
            "id": self.id,
            "titulo": self.titulo,
            "autor": self.autor,
            "categoria": self.categoria,
            "categorias": self.categorias_lista(),
            "ano": self.ano,
            "quantidade": self.quantidade,
            "status": self.status,
            "sinopse": self.sinopse,
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            id=data.get("id"),
            titulo=data.get("titulo"),
            autor=data.get("autor"),
            categoria=data.get("categoria", ""),
            ano=data.get("ano"),
            quantidade=data.get("quantidade"),
            status=data.get("status", cls.STATUS_DISPONIVEL),
            sinopse=data.get("sinopse", ""),
        )

    def validate(self):
        errors = []
        if not self.titulo or not self.titulo.strip():
            errors.append("Título é obrigatório")
        if not self.autor or not self.autor.strip():
            errors.append("Autor é obrigatório")
        if not self.categoria or not self.categoria.strip():
            errors.append("Categoria é obrigatória")
        try:
            ano = int(self.ano)
            if ano < 1 or ano > 2100:
                errors.append("Ano de publicação inválido")
        except (ValueError, TypeError):
            errors.append("Ano de publicação deve ser um número")
        try:
            qtd = int(self.quantidade)
            if qtd < 0:
                errors.append("Quantidade não pode ser negativa")
        except (ValueError, TypeError):
            errors.append("Quantidade deve ser um número inteiro")
        if self.status not in [self.STATUS_DISPONIVEL, self.STATUS_EMPRESTADO]:
            errors.append("Status inválido. Use 'Disponivel' ou 'Emprestado'")
        return errors
