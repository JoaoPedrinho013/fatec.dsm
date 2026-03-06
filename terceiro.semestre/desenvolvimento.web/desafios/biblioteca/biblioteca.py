class Biblioteca:
    
    def __init__(self, _listaLivros = [], _listaUsers = []):
        self.listaLivros = _listaLivros
        self.listaUsers = _listaUsers

    def addLivro(self, _livro):
        self.listaLivros.append(_livro)
        print(f'Livro {_livro.titulo} foi adcionado com sucesso!')

    def addUser(self, _users):
        self.listaUsers.append(_users)
        print(f'User {_users.nome} foi adcionado com sucesso!')

    def getLivrosDisponiveis(self):
        encontrou = False
        indice = 1
        for livro in self.listaLivros:
            if livro.disponivel:
                print(f'\n------ Livro {indice} ------')
                print(livro)
                encontrou = True
                indice += 1
            

        if not encontrou:
            print("Não há livros disponíveis no momento.")

    def getLivrosEmprestados(self):
        encontrou = False
        indice = 1
        for livro in self.listaLivros:
            if not livro.disponivel:
                print(f'\n------ Livro Emprestado {indice} ------')
                print(livro)
                encontrou = True
                indice += 1

        if not encontrou:
            print("Não há livros emprestados no momento.")

    def __str__(self):
        return (
            "Lista de Livros:\n" +
            "\n".join(str(livro) for livro in self.listaLivros)
        )


