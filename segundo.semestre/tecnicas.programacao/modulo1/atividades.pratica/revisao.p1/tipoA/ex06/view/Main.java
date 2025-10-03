package view;
import model.Carro;

public class Main {
    public static void main(String[] args) {
    Carro carro1 = new Carro();
    carro1.marca = "Honda";
    carro1.modelo = "Civic";
    carro1.ano = 1999;

    System.out.println("Marca do carro: " + carro1.marca);
    System.out.println("Modelo do carro: " + carro1.modelo);
    System.out.println("Ano do carro: " + carro1.ano);
}
}