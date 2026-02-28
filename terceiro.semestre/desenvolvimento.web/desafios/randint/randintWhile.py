import random
numerosGerados = []
numerosUser = []
def gerarNumeros(limite):
    while len(numerosGerados) < limite:
        num = random.randint(1,10)
        if num not in numerosGerados:
            numerosGerados.append(num)

while len(numerosUser) < 6:
    num = int(input("Digite um número de 1 a 10: "))
    if num not in numerosUser:
        numerosUser.append(num)
    else:
        print("Número repetido!")

def compararListas(lista1, lista2):
    acertos = 0
    for numero in lista2:
        if numero in lista1:
            acertos += 1
    return acertos

gerarNumeros(6)
acertos = compararListas(numerosGerados, numerosUser)
print(f"Gerados: {numerosGerados}")
print(f"Usuário: {numerosUser}")
print(f"Acertos: {acertos}")
