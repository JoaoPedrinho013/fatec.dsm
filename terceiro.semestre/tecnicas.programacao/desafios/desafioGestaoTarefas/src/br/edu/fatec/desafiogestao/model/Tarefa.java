package br.edu.fatec.desafiogestao.model;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private Boolean concluida;
    private String categoria;

    public Tarefa(String titulo, String descricao, Boolean concluida, String categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = concluida;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", concluida=" + concluida +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
