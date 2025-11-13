package model;

public class VideoLocadora {
    private String[] filmes;
    private int[] vezesAlugados;
    private double[] precosLocacao;

    public VideoLocadora(String[] filmes, int[] vezesAlugados, double[] precoLocacao) {
        this.filmes = filmes;
        this.vezesAlugados = vezesAlugados;
        this.precosLocacao = precoLocacao;
    }

    public void exibirFilmes() {
        for (int c = 0; c < filmes.length; c++) {
            System.out.println(
                    filmes[c] + " || Preços: " + precosLocacao[c] + " || Alugado: " + vezesAlugados[c] + " vezes.");
        }
    }

    public void calcularValorTotal() {
        double faturamentoTotal = 0;

        for (int c = 0; c < filmes.length; c++) {
            faturamentoTotal += precosLocacao[c];
        }

        System.out.println("Valor total do faturamento se alugar todos os filmes: " + faturamentoTotal);
    }

    public void filmeMaisAlugado() {
        String filmeAlugado = filmes[0];
        int maisAlugado = vezesAlugados[0];

        for (int c = 0; c < filmes.length; c++) {
            if (vezesAlugados[c] > maisAlugado) {
                maisAlugado = vezesAlugados[c];
                filmeAlugado = filmes[c];
            }
        }
        System.out.println("Filme mais alugado: " + filmeAlugado + " || Alugado: " + maisAlugado);
    }

    public void alugarFilme(String nome) {
        for (int c = 0; c < filmes.length; c++) {
            if (filmes[c].equals(nome)) {
                vezesAlugados[c]++;
            }
        }
        System.out.println("Filme alugado");
    }

    public void devolverFilme(String nome) {
        for (int i = 0; i < filmes.length; i++) {
            if (filmes[i].equals(nome)) {
                System.out.println("Filme devolvido.");
            }
        }
    }

}
