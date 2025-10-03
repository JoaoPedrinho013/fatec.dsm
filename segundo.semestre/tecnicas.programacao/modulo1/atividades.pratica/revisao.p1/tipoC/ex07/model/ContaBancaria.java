package model;

public class ContaBancaria {
    private String titular;
    private int numeroConta;
    private double saldo;

    public String getTitular(){
        return this.titular;
    }
    public void setTitular(String nome) {
        this.titular = nome;
    }
     public int getNumeroConta(){
        return this.numeroConta;
    }
    public void setNumeroConta(int conta) {
        this.numeroConta = conta;
    }
     public double getSaldo(){
        return this.saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
