package model.jogo;

public class Jogo {
    public String nomeJogador = "Sem jogador";
    public String idJogador = "0";
    public double pontuacaoTotal = 0;
    public int nivelAtual = 0;
    public double tempoJogo = 0;

    

    public void iniciarPartida(String idJog){
        if(nomeJogador != "Sem jogador" && idJogador.equals("0")){
            idJogador = nomeJogador;
        }
        System.out.println("Abrindo o jogo com o id do player: " + idJogador);
    }
    public void salvarProgresso(int nivel, double tempo, String nome){
        System.out.println("Salvamento feito com sucesso, "+ nome + ".");
        System.out.println("Seu status antes de salvar, nivel: " + nivelAtual + " e Tempo de Jogo: "+ tempoJogo + "Hrs");
        nivelAtual += nivel;
        tempoJogo += tempo;
        System.out.println("-------------------------------------------");
        System.out.println("Seu status depois de salvar, nivel: " + nivelAtual + " e Tempo de Jogo: "+ String.format("%.2f", tempoJogo) + "Hrs");
    }
    public void detalhes(){
        System.out.println("Nome do Jogador: " + nomeJogador);
        System.out.println("ID do Jogador: " + idJogador);
        System.out.println("Pontuação Total: " + pontuacaoTotal);
        System.out.println("Nivel Atual: " + nivelAtual);
        System.out.println("Tempo de Jogo: " + String.format("%.2f", tempoJogo) + "Hrs");
    }
}
