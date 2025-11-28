package SistemaBancario.model;

// ============================================
// HERANÇA - ContaPoupanca herda de Conta
// ============================================
public class ContaPoupanca extends Conta {
    private double taxaRendimento;
    
    // CONSTRUTOR
    public ContaPoupanca(String titular, int numeroConta, double saldoInicial) {
        super(titular, numeroConta, saldoInicial);
        this.taxaRendimento = 0.005; // 0.5% ao mês
    }
    
    // GETTER E SETTER
    public double getTaxaRendimento() {
        return taxaRendimento;
    }
    
    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
    
    // POLIMORFISMO - implementação específica
    @Override
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= getSaldo()) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
            return true;
        } else {
            System.out.println("Saldo insuficiente! Saldo atual: R$" + getSaldo());
            return false;
        }
    }
    
    // POLIMORFISMO - poupança não tem taxa
    @Override
    public double calcularTaxa() {
        return 0.0;
    }
    
    // MÉTODO ESPECÍFICO da ContaPoupanca
    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaRendimento;
        setSaldo(getSaldo() + rendimento);
        System.out.println("Rendimento de R$" + String.format("%.2f", rendimento) + " aplicado!");
    }
    
    // POLIMORFISMO - sobrescrita do toString
    @Override
    public String toString() {
        return super.toString() + 
               " | Rendimento: " + (taxaRendimento * 100) + "%" + 
               " | Tipo: Conta Poupança";
    }
}

