package view;

import model.Produto;

public class Main {
     public static void main(String[] args) {
        Produto camisa = new Produto();

        camisa.setNome("Iphone 14 Pro Max");

        camisa.setPreco(7999.99);
        
        camisa.setEstoque(10);
    }
}