package view;
import model.Livro;

public class Main {
    public static void main(String[] args) {
    
        Livro harryPotter = new Livro();
        harryPotter.titulo = "Harry Potter e a Pedra Filosofal";
        harryPotter.autor = "J.K Rowling";
        harryPotter.anoPublicacao = 1997;

        System.out.println("Título: " + harryPotter.titulo);
        System.out.println("Autor: " + harryPotter.autor);
        System.out.println("Ano de Publicação: " + harryPotter.anoPublicacao);
}
}