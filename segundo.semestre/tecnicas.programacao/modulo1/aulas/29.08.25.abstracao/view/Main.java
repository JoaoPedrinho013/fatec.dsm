package view;
import model.Carro;

public class Main {
    public static void main(String[] args){
        System.out.println("Main funcionando");

        Carro meuCarro = new Carro();

        meuCarro.marca = "Honda";
        meuCarro.modelo = "Civic";
        meuCarro.ano = 2023;

        // System.out.println(meuCarro.ano);

        Carro meuCarroNovo = new Carro();

        meuCarroNovo.ano=2024;
        // System.out.println(meuCarroNovo.ano);

        meuCarro.acelerar();
        meuCarro.ligar();
        meuCarro.acelerar();
    }
}