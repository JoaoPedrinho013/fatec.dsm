print("Digite 2 números inteiros positivos para um intervalo.")

while True:
    inicio = input("Digite o início: ")
    if inicio.isdigit():
        inicio = int(inicio)
        break
    else:
        print("Digite apenas números inteiros positivos!")

while True:
    fim = input("Digite o fim: ")
    if fim.isdigit():
        fim = int(fim)
        if fim > inicio:
            break
        else:
            print("O fim precisa ser maior que o início!")
    else:
        print("Digite apenas números inteiros positivos!")


def isPrimo(numero):
    if numero <= 1:
        return False
    
    for i in range(2, int(numero**0.5) + 1):
        if numero % i == 0:
            return False
    return True


numerosPrimos = []

for numero in range(inicio, fim + 1):
    if isPrimo(numero):
        numerosPrimos.append(numero)


print(f"\nIntervalo: {inicio} até {fim}")

if numerosPrimos:
    print(f"Quantidade: {len(numerosPrimos)}")
    print(f"Menor: {min(numerosPrimos)}")
    print(f"Maior: {max(numerosPrimos)}")
    print(f"Números primos: {numerosPrimos}")
else:
    print("Não existem números primos nesse intervalo.")