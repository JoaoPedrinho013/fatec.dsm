from myLib import *

biblioteca = Biblioteca()

while True:
    opcao = input("""
1 - Cadastrar livro
2 - Cadastrar usuario
3 - Pegar livro 
4 - Devolver livro
5 - Listar livros disponiveis
6 - Listar livros emprestados
7 - Sair
Escolha uma opção: """)

    match opcao:
        case "1":
            titulo = input("Titulo: ")
            autor = input("Autor: ")
            ano = input("Ano: ")
            isDigital = input("Digital (Sim / Nao): ").lower().strip()

            livroExiste = False

            for livro in biblioteca.listaLivros:
                if livro.titulo.lower() == titulo.lower():
                    livroExiste = True
                    break

            if livroExiste:
                print("Esse livro já está cadastrado!")
                time.sleep(1.5)
            else:
                if isDigital == 'sim' or isDigital == 's':
                    tamanho = input('Tamanho do arquivo(Mb): ')
                    livro = LivroDigital(titulo, autor, ano, tamanho)
                else:
                    livro = Livro(titulo, autor, ano)

                biblioteca.addLivro(livro)
                time.sleep(1.5)

        case "2":
            nome = input("Nome: ")
            matricula = input("Matricula: ")

            userExiste = False

            for user in biblioteca.listaUsers:
                if user.nome.lower() == nome.lower():
                    userExiste = True
                    break

            if userExiste:
                print("Esse usuário já está cadastrado!")
                time.sleep(1.5)
            else:
                user = User(nome, matricula)
                biblioteca.addUser(user)
                time.sleep(1.5)

        case "3":
            nameUser = input("Usuario: ")
            nameLivro = input("Titulo: ")

            usuario = None
            for user in biblioteca.listaUsers:
                if user.nome.lower() == nameUser.lower():
                    usuario = user
                    break

            livro = None
            for obra in biblioteca.listaLivros:
                if obra.titulo.lower() == nameLivro.lower():
                    livro = obra
                    break

            if usuario is None:
                print("Usuário não encontrado!")
            elif livro is None:
                print("Livro não encontrado!")
            else:
                usuario.pegarEmprestado(livro)

            time.sleep(1.5)

        case "4":
            nameUser = input("Usuario: ")
            nameLivro = input("Titulo: ")

            usuario = None
            for user in biblioteca.listaUsers:
                if user.nome.lower() == nameUser.lower():
                    usuario = user
                    break

            livro = None
            for obra in biblioteca.listaLivros:
                if obra.titulo.lower() == nameLivro.lower():
                    livro = obra
                    break

            if usuario is None:
                print("Usuário não encontrado!")
            elif livro is None:
                print("Livro não encontrado!")
            else:
                usuario.devolverLivro(livro)

            time.sleep(1.5)

        case "5":
            biblioteca.getLivrosDisponiveis()
            time.sleep(1.5)

        case "6":
            biblioteca.getLivrosEmprestados()
            time.sleep(1.5)

        case "7":
            print("Saindo do programa...")
            time.sleep(1.5)
            break

        case _:
            print("Opção inválida")
