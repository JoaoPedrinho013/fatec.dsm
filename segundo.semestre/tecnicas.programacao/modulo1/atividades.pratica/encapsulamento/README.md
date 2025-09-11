# Atividade Prática 5 - Encapsulamento

## Lista de Exercícios

### 1. Classe Carro
Evolua o conceito **Carro** do exercício e defina 1 método para calcular o valor total para encher o tanque.  
Este método deve receber como parâmetro o valor da gasolina.  

- Crie um atributo `capacidadeTanque` na classe.  
- O usuário deve entrar com as informações do carro.  
- Crie métodos específicos para fornecer e obter os valores dos atributos (`set/get`), caso aplicável.  

---

### 2. Classe ContaBancaria
Crie uma classe **ContaBancaria** com os atributos privados `saldo` e `titular`.  

- O saldo só pode ser alterado através de um método público chamado `depositar` e `sacar`.  
- O método `depositar` recebe como parâmetro o valor a ser depositado e só permite valores positivos.  
- O método `sacar` recebe um valor e subtrai do saldo, mas só se o valor for menor ou igual ao saldo atual.  
- Crie instâncias dessa classe no método `main` e teste as operações.  

---

### 3. Classe Produto
Implemente uma classe **Produto** com os atributos privados `nome`, `preco` e `quantidadeEstoque`.  

- Crie métodos **getters** e **setters** para esses atributos.  
- Adicione uma regra no setter de preço que impede a definição de valores negativos.  
- No setter de `quantidadeEstoque`, o valor deve ser ajustado apenas se for maior ou igual a zero.  
- No método `main`, crie um objeto Produto e teste as restrições definidas.  
