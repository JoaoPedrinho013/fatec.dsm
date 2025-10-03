package model;

public class Aluno {
    private String nome;
    private String matricula;
    private double notaFinal;

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getMatricula(){
        return this.matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public double getNotaFinal(){
        return this.notaFinal;
    }
    public void setNotaFinal(double nota1, double nota2, double trabalho) {
        this.notaFinal = (nota1 + nota2 + trabalho) / 3;
    }

}
