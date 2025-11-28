package model;

public class ContaCorrente extends Conta {

    public ContaCorrente(int agencia, int conta, String cliente) {
        super(agencia, conta, cliente); 
    }

    @Override
    public void cobrarTaxa(){
        this.depositarSaldo(-0.99);
    }
    
}
