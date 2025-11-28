package SistemaBancario.view;

import java.util.ArrayList;

import SistemaBancario.model.Cliente;
import SistemaBancario.model.Conta;
import SistemaBancario.model.ContaCorrente;
import SistemaBancario.model.ContaPoupanca;

public class Main {
    public static void main(String[] args) {  // ⬅️ main minúsculo!
        ArrayList<Conta> contas = new ArrayList<>();
        
        System.out.println("=== SISTEMA BANCÁRIO ===\n");
        
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00", "(11) 98765-4321");
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-00", "(11) 91234-5678");
        
        System.out.println(cliente1);
        System.out.println(cliente2);
        System.out.println();
        
        Conta cc1 = new ContaCorrente("João Silva", 1001, 1000.0, 500.0);
        Conta cp1 = new ContaPoupanca("Maria Santos", 2001, 2000.0);
        
        contas.add(cc1);
        contas.add(cp1);
        
        System.out.println("=== CONTAS CADASTRADAS ===");
        for (Conta conta : contas) {
            System.out.println(conta);
        }
        System.out.println();
        
        System.out.println("=== OPERAÇÕES BANCÁRIAS ===");
        
        System.out.println("\n--- Depósitos ---");
        cc1.depositar(500.0);
        cp1.depositar(1000.0);
        
        System.out.println("\n--- Saques ---");
        cc1.sacar(1800.0);
        cp1.sacar(500.0);
        
        System.out.println("\n--- Operações Específicas ---");
        if (cc1 instanceof ContaCorrente) {
            ContaCorrente contaCorrente = (ContaCorrente) cc1;
            contaCorrente.cobrarTaxa();
        }
        
        if (cp1 instanceof ContaPoupanca) {
            ContaPoupanca contaPoupanca = (ContaPoupanca) cp1;
            contaPoupanca.aplicarRendimento();
        }
        
        System.out.println("\n--- Taxas das Contas ---");
        for (Conta conta : contas) {
            System.out.println("Conta " + conta.getNumeroConta() + 
                             " - Taxa: R$" + conta.calcularTaxa());
        }
        
        System.out.println("\n=== SALDOS FINAIS ===");
        for (Conta conta : contas) {
            System.out.println(conta);
        }
    }
}