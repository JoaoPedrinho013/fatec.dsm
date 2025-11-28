package SistemaBancario.model;

// ============================================
// CLASSE ABSTRATA (Abstração)
// ============================================
public abstract class Conta {
    // ENCAPSULAMENTO - atributos privados
    private String titular;
    private int numeroConta;
    private double saldo;
    
    // CONSTRUTOR
    public Conta(String titular, int numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }
    
    // GETTERS E SETTERS (Encapsulamento)
    public String getTitular() {
        return titular;
    }
    
    public void setTitular(String titular) {
        this.titular = titular;
    }
    
    public int getNumeroConta() {
        return numeroConta;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    // MÉTODOS CONCRETOS
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito!");
        }
    }
    
    // MÉTODO ABSTRATO (Abstração) - cada tipo de conta implementa seu saque
    public abstract boolean sacar(double valor);
    
    // MÉTODO ABSTRATO para calcular taxas
    public abstract double calcularTaxa();
    
    // TO STRING
    @Override
    public String toString() {
        return "Conta: " + numeroConta + 
               " | Titular: " + titular + 
               " | Saldo: R$" + String.format("%.2f", saldo);
    }
}
