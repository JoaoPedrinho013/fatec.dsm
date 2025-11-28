package SistemaBancario.model;

// ============================================
// HERANÇA - ContaCorrente herda de Conta
// ============================================
public class ContaCorrente extends Conta {
    private double limite;
    private double taxaManutencao;
    
    // CONSTRUTOR - chama o construtor da classe pai
    public ContaCorrente(String titular, int numeroConta, double saldoInicial, double limite) {
        super(titular, numeroConta, saldoInicial);
        this.limite = limite;
        this.taxaManutencao = 15.0;
    }
    
    // GETTER E SETTER
    public double getLimite() {
        return limite;
    }
    
    public void setLimite(double limite) {
        this.limite = limite;
    }
    
    // POLIMORFISMO - implementação específica do método abstrato
    @Override
    public boolean sacar(double valor) {
        double saldoDisponivel = getSaldo() + limite;
        if (valor > 0 && valor <= saldoDisponivel) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
            return true;
        } else {
            System.out.println("Saldo insuficiente! Saldo disponível: R$" + saldoDisponivel);
            return false;
        }
    }
    
    // POLIMORFISMO - implementação do cálculo de taxa
    @Override
    public double calcularTaxa() {
        return taxaManutencao;
    }
    
    public void cobrarTaxa() {
        double taxa = calcularTaxa();
        setSaldo(getSaldo() - taxa);
        System.out.println("Taxa de manutenção de R$" + taxa + " cobrada.");
    }
    
    // POLIMORFISMO - sobrescrita do toString
    @Override
    public String toString() {
        return super.toString() + 
               " | Limite: R$" + String.format("%.2f", limite) + 
               " | Tipo: Conta Corrente";
    }
}

