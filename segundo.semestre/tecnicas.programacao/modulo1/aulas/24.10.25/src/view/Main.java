package src.view;

import src.model.ContaCorrente;
import src.model.Poupanca;

public class Main {

    public static void main(String[] args) {
        ContaCorrente contaC = new ContaCorrente(1000, 420, "João");
        Poupanca contaP = new Poupanca(1000, 421, "Pedro");

        contaC.depositarSaldo(500);
        contaC.cobrarTaxa();
        System.out.println(contaC);

        contaP.depositarSaldo(100);
        contaP.depositarSaldo(450, 2);
        contaP.cobrarTaxa();
        System.out.println(contaP);
    }
}