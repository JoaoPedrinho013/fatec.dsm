package view;

import model.VideoLocadora;

public class Main {
    public static void main(String[] args) {
        String[] filmes = {"Harry-Potter", "Vingadores", "Liga da Justiça"};
        int[] vezesAlugados = {10, 15, 5};
        double[] precoLocacao = {24.99, 29.99, 26.99};

        VideoLocadora netflix = new VideoLocadora(filmes, vezesAlugados, precoLocacao);

        netflix.exibirFilmes();
        netflix.calcularValorTotal();
        netflix.filmeMaisAlugado();
        netflix.alugarFilme("Vingadores");
        netflix.devolverFilme("Vingadores");
    }
}
