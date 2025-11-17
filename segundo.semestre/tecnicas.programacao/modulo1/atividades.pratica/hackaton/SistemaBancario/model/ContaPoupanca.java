package model;

public class ContaPoupanca extends Conta {
    private double saldoPoupanca;

    public ContaPoupanca(int agencia, int conta, String cliente) {
        super(agencia, conta, cliente);
        this.saldoPoupanca = 0;
    }

    public void depositarSaldo(double valor, int op){
        if(op == 1) {
            this.depositarSaldo(valor);
        } else if (op ==2) {
            this.saldoPoupanca += valor;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "| Poupança: " + this.saldoPoupanca;
    }
    
}
