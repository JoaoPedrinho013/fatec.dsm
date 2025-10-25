package src.model;

public abstract class Conta {
    private int agencia;
    private int conta;
    private String cliente;
    private double saldo;

    public Conta(int agencia, int conta, String cliente) {
        this.agencia = agencia;
        this.conta = conta;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double exibirSaldo() {
        return saldo;
    }

    public void depositarSaldo(double valor) {
        this.saldo += valor;
    }

    public void cobrarTaxa(){
        this.saldo -= 0;
    }
    @Override
    public String toString() {
        return "Conta [agencia=" + agencia + ", conta=" + conta + ", cliente=" + cliente + ", saldo=" + saldo + "]";
    }

}
