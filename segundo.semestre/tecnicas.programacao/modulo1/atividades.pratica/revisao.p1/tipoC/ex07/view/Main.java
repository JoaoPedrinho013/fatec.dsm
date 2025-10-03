package view;
import model.ContaBancaria;

public class Main {
    public static void main(String[] args) {
    
        ContaBancaria conta1 = new ContaBancaria();
        conta1.setTitular("João");
        conta1.setNumeroConta(4002);
        conta1.setSaldo(3987345.98);

        System.out.println("Titular da conta: " + conta1.getTitular());
        System.out.println("Número da conta: " + conta1.getNumeroConta());
        System.out.println("Saldo da conta: R$" + String.format("%.2f", conta1.getSaldo()));
}
}