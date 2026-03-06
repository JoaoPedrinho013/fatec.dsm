class User:

    def __init__(self, _nome, _matricula, _listaLivrosEmprestado = None):
        self.nome = _nome
        self.matricula = _matricula
        self.listaLivrosEmprestado = _listaLivrosEmprestado if _listaLivrosEmprestado is not None else []

    def pegarEmprestado(self, _livro):
        if _livro.emprestar():
            self.listaLivrosEmprestado.append(_livro)

    def devolverLivro(self, _livro):
        if _livro.devolver():
            self.listaLivrosEmprestado.remove(_livro)
            
    def __str__(self):
        return(f'Nome: {self.nome}\n'
               f'Matricula: {self.matricula}\n'
               f'Lista de Livros Emprestado: {self.listaLivrosEmprestado}\n')
