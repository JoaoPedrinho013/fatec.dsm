from funcoes import quadrado
import funcoes

funcoes.produto(10,2)
quadrado(6)

def saudacao(nome, aula="WEB III"):
    print(f"Olá, {nome}, seja bem-vindo a aula de {aula}!")

saudacao("Azien", "Python")

def operacoes(a, b):
    return (a + b), (a * b), (b - a), (b / a)

print(operacoes(2,3))


print("Fim da Função")
