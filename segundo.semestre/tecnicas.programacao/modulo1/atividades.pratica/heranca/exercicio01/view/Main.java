package exercicio01.view;

import exercicio01.model.Calculadora;

public class Main {
    public static void main(String[] args) {
        
        Calculadora calc = new Calculadora(10, 4);
        System.out.println("Subtração: " + calc.subtracao());
        System.out.println("Divisão: " + calc.divisao());
        System.out.println("Multiplicação: " + calc.multiplicacao());
        System.out.println("Soma: " + calc.soma());

    }
}
