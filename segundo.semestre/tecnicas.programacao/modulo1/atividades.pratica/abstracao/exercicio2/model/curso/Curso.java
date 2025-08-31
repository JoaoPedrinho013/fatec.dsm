package model.curso;

public class Curso {
    public String materias, professores, nomeCurso, matriculas;
    public double cargaHoraria;

    public void fazerMatricula(String matricula){
        System.out.println("Criando a matricula: " + matricula);
        matriculas = matricula;
    }
    public void encerrarMatricula(String matricula){
        System.out.println("Removendo a matricula " + matricula);
        if(matriculas == matricula){
            matriculas = null;
        }
    }
    public void detalhes(){
        System.out.println("Materias: " + materias);
        System.out.println("Professores: " + professores);
        System.out.println("Nome do Curso: " + nomeCurso);
        System.out.println("Matriculas: " + matriculas);
        System.out.println("Carga Horaria: " + cargaHoraria);
    }
}
