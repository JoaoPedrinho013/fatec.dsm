import random
numerosGerados = []
numerosUser = []
def gerarNumeros(limite):
    for n in range(limite):
        numerosGerados.append(random.randint(1, 10))

for n in range(6):
    numerosUser.append(int(input("Digite um número de 1 a 10: ")))

def compararListas(lista1, lista2):
    acertos = 0
    for numero in lista2:
        if numero in lista1:
            acertos += 1
    return acertos

gerarNumeros(6)
acertos = compararListas(numerosGerados, numerosUser)
print("Gerados:", numerosGerados)
print("Usuário:", numerosUser)
print("Acertos:", acertos)
