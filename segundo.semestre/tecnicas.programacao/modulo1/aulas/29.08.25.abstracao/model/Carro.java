package model;
public class Carro{
    public String marca;
    public String modelo;
    public int ano;

    private boolean ligado = false;

    public void ligar(){
        if(!ligado) {
            ligado = true;
            System.out.println("o carro ligado.");
        } else {
            System.out.println("O carro ja está ligado.");
        }
    }

    public void acelerar() {
        if(ligado){
            System.out.println("Carro andando");
        } else {
            System.out.println("Ligue o carro primeiro");
            this.ligar();
            this.acelerar();
        }
    }

    public void desligar() {
        if (ligado) {
            ligado = false;
            System.out.println("Carro desligado");
        }else {
            System.out.println("O carro já está desligado ligue ele primeiro");
        }
    }
}
