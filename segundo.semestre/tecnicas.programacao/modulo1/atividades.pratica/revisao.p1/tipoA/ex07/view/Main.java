package view;
import model.Aluno;

public class Main {
    public static void main(String[] args) {
    Aluno aluno1 = new Aluno();
    aluno1.setNome("João");
    aluno1.setMatricula("f3987pdr13");
    aluno1.setNotaFinal(9.5, 8.75, 8.25);

    System.out.println("Nome do Aluno: " + aluno1.getNome());
    System.out.println("Matricula do Aluno: " + aluno1.getMatricula());
    System.out.println("Nota Final do Aluno: " + String.format("%.2f", aluno1.getNotaFinal()));
}
}