package model.contaBancaria;
public class ContaBancaria {
    public String  agencia, conta, tipoConta, nomeCliente, cpfCliente;

    public void exibirSaldo(String saldo){
        System.out.println("Seu saldo é de " + saldo);
    }
    public void realizarTransferencia(String conta, double valor){
        System.out.println("Realizar Transferencia para " + conta + " com valor de R$" + valor);
    }
    public void fazerPagamento(String codigo, double valor, String metodo){
        System.out.println("Pagar a conta " + codigo + " no valor de R$" + valor + " usando o " + metodo);
    }
    public void detalhes(){
        System.out.println("Nome do Cliente: " + nomeCliente);
        System.out.println("CPF do Cliente: " + cpfCliente);
        System.out.println("Tipo de Conta: " + tipoConta);
        System.out.println("Agência: " + agencia);
        System.out.println("Conta: " + conta);
    }
}
