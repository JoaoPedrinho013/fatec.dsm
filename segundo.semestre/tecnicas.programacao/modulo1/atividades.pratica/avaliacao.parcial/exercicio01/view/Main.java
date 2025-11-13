package view;
import model.Loja;

public class Main {
    
    public static void main(String[] args) {
        String[] nomes = {"Mouse", "Teclado", "Monitor"};
        int[] quantidades = {10, 5, 3};
        double[] precos = {25.44, 82.28, 307.73};

        Loja pichau = new Loja(nomes, quantidades, precos);

        pichau.exibirProdutos();
        pichau.calcularCaroBarato();
        pichau.calcularValorTotal();
        pichau.compraProduto("Teclado", 3);
        pichau.reporProduto("Mouse" , 1);
    }
}
