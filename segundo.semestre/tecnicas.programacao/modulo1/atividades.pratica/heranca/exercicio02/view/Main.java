package exercicio02.view;

import exercicio02.model.PagamentoCartao;
import exercicio02.model.PagamentoDinheiro;

public class Main {
    public static void main(String[] args) {
        
        PagamentoDinheiro reais = new PagamentoDinheiro(100, 0.1);
        System.out.println(reais.calcularPagamento());
        System.out.println(reais.emitirRecibo());
        System.out.println();
        PagamentoCartao nubank = new PagamentoCartao(100, 0.05);
        System.out.println(nubank.calcularPagamento());
        System.out.println(nubank.emitirRecibo());

    }
}
