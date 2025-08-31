package model.projeto;

public class Projeto {
    public String nome;
    public String descricao;
    public String dataInicio;
    public String prazoFinal;
    public String objetivo;

    public void iniciarProjeto() {
        System.out.println("Projeto " + nome + " iniciado.");
    }
    public void finalizarProjeto() {
        System.out.println("Projeto " + nome + " finalizado.");
    }
    public void detalhes() {
        System.out.println("Detalhes do Projeto:");
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Data de Início: " + dataInicio);
        System.out.println("Prazo Final: " + prazoFinal);
        System.out.println("Objetivo: " + objetivo);
    }
}
