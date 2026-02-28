limite = 25
pesoTotal = 0
itens = []

nome = input("\nDigite o nome do mochileiro: ")

while True:
    item = input("\nDigite o nome do item ou 'fim' para encerrar: ")
    if item == "fim":
        break
    peso = float(input("\nDigite o peso do item (kg): "))
    if pesoTotal + peso <= limite:
        itens.append((item, peso))
        pesoTotal += peso
        print("Item adcionado com sucesso!")
    else:
        print("Peso execede o limite da mochila. Item não adcionado")
        if len(itens) > 0:
            break
espacoRestante = limite - pesoTotal
print(f"\nMochileiro: {nome}")
print(f"Peso total: {pesoTotal:.2f}Kg")
print(f"Espaço restante: {espacoRestante:.2f}Kg")
if len(itens) <= 0:
    print("Mochila está vazia")
else:
    print("Itens adcionados:")
    for item, peso in itens:
        print(f"-{item}: {peso:.2f}Kg")