import re
import time

contatos = {
    'Teste' : '(13) 99999-9999'
}

def validarTelefone(mensagem="Digite o telefone: ", permitir_vazio=False):
    padrao = r'^\(\d{2}\) \d{5}-\d{4}$'
    
    while True:
        telefone = input(mensagem).strip()

        if permitir_vazio and telefone == "":
            return ""

        if re.match(padrao, telefone):
            return telefone
        else:
            print("Formato inválido! Use: (99) 99999-9999")

def createContato():
    nome = input("Digite o nome de contato: ").title().strip()
    
    if nome in contatos:
        print(f"O contato '{nome}' já existe!")
        
        res = input("Deseja atualizar este contato? (S/N)\n Não será possível desfazer! ").strip().lower()
        
        if res == "s":
            updateContato(nome)
        else:
            print("Voltando ao menu...")
            time.sleep(1)
        
        return
    
    telefone = validarTelefone()
    
    contatos[nome] = telefone
    print("Contato criado com sucesso!")
    time.sleep(1)

def getAllContatos():
    for chave, valor in contatos.items():
        print(f'{chave} -> {valor}')
    time.sleep(1)         

def getContato():
    nome = input("Digite o nome do contato: ").title().strip()
    if nome in contatos:
        print("Contato encontrado: ")
        print(f"{nome} -> {contatos[nome]}")
        time.sleep(1)
    else:
        print("Contato não encontrado. Voltando ao menu...")
        time.sleep(1)

def updateContato(name=None):
    if name:
        nome = name
    else:
        nome = input("Digite o nome do contato a ser atualizado: ").title().strip()
    
    if nome in contatos:
        print(f"Contato atual: {nome} -> {contatos[nome]}")
        
        nomeAtt = input("Digite o nome novo (ou Enter para não alterar): ").title().strip()
        
        telefoneAtt = validarTelefone(
            "Digite o numero novo (ou Enter para não alterar): ",
            permitir_vazio=True
        )
        
        if nomeAtt:
            contatos[nomeAtt] = contatos.pop(nome)
            nome = nomeAtt 
        
        if telefoneAtt:
            contatos[nome] = telefoneAtt
        
        print("Contato atualizado com sucesso!")
        time.sleep(1)
        
    else:
        print("Contato não encontrado.")
        time.sleep(1)

def deleteContato():
    nome = input("Digite o nome do contato que você quer deletar: ").title().strip()
    if nome in contatos:
        res = input(f"Contato {nome} encontrado: {contatos[nome]}, deseja remove-lo? (S/N)")
        if res.lower().strip() == "s":
            del contatos[nome]
            time.sleep(0.5)
            print("Contato deletado com sucesso!")
        else:
            print("Voltando ao menu...")
    else:
        print("Contato não encontrado. Voltando ao menu...")   
    time.sleep(1)

def AppContatos():
    while True:
        print("\n--- CONTATOS ---")
        print("1 - Cadastrar um contato")
        print("2 - Buscar um contato")
        print("3 - Listar todos contatos")
        print("4 - Atualizar um contato")
        print("5 - Remover um contato")
        print("9 - Sair")

        opcao = input("Escolha uma opção: ").strip()

        match opcao:
            case "1":
                createContato()
            
            case "2":
                getContato()
            
            case "3":
                getAllContatos()
                 
            case "4":
                updateContato()

            case "5":
                deleteContato()

            case "9":
                print("Encerrando app contatos...")
                break

            case _:
                print("Opção inválida!")



AppContatos()