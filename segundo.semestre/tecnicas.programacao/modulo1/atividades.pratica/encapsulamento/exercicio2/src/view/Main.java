package view;

import model.ContaBancaria;

public class Main {
    
    public static void main(String[] args) {
        
        ContaBancaria nubank = new ContaBancaria();
        
        nubank.setDepositar(1550.25 , " Pedro");

        nubank.setSaldo(nubank.getSaldo());

        nubank.setSacar(530.00 , " Pedro");

        nubank.setSaldo(nubank.getSaldo());
    }
}