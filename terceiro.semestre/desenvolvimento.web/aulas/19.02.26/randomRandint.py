import random

def numAleatorios():
    listPC = []
    i = 1
    while i <= 5:
        numero = random.randint(1, 10)
        if numero not in listPC:
            listPC.append(numero)
            i+=1
    return listPC

numPC = numAleatorios()

listUser = []
x=1
while x <= 5:
    num = int(input("Digite um número: "))
    if num not in listUser:
        listUser.append(num)
        x+=1

def acertos(pc, user):
    pontos = 0
    for n in user:
       if n in pc:
           pontos += 1
    return pontos

pontuacao = acertos(numPC, listUser)
print(f"Gerados: {numPC}")
print(f"User: {listUser}")
print(f"Acertos: {pontuacao}")
