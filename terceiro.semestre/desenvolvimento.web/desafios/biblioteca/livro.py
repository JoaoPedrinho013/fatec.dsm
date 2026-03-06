class Livro:

    def __init__(self, _titulo, _autor, _ano, _disponivel=True):
        self.titulo = _titulo
        self.autor = _autor
        self.ano = _ano
        self.disponivel = _disponivel

    def emprestar(self):
        if self.disponivel:
            self.disponivel = False
            print(f'Livro {self.titulo} emprestado com sucesso!')
            return True
        else:
            print(f'Livro {self.titulo} não está disponivel, volte depois!')
            return False

    def devolver(self):
        if self.disponivel:
            print(f'Livro {self.titulo} já está conosco!')
            return False
        else:
            self.disponivel = True
            print(f'Livro {self.titulo} devolvido com sucesso!')
            return True

    def __str__(self):
        return (f'Titulo: {self.titulo}\n'
                f'Autor: {self.autor}\n'
                f'Ano: {self.ano}\n'
                f'Disponivel: {"Sim" if self.disponivel else "Não"}\n')


class LivroDigital(Livro):
    def __init__(self, _titulo, _autor, _ano,  _tamanhoArquivo, _disponivel=True):
        super().__init__(_titulo, _autor, _ano, _disponivel)
        self.tamanhoArquivo = _tamanhoArquivo

    def __str__(self):
        return super().__str__() + f'Tamanho do Arquivo: {self.tamanhoArquivo} MB'

