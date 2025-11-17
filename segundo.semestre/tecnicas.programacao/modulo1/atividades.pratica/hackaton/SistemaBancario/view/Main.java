package view;

import model.ContaCorrente;
import model.ContaPoupanca;

public class Main {

    public static void main(String[] args) {
        ContaCorrente contaC = new ContaCorrente(1000, 420, "João");
        ContaPoupanca contaP = new ContaPoupanca(1000, 421, "Pedro");

        contaC.depositarSaldo(500);
        contaC.cobrarTaxa();
        System.out.println(contaC);

        contaP.depositarSaldo(100);
        contaP.depositarSaldo(450, 2);
        contaP.cobrarTaxa();
        System.out.println(contaP);
    }
}