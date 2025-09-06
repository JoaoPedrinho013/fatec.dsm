package encapsulamento.view;

import encapsulamento.model.Carro;

public class Main {
    public static void main(String[] args) throws Exception{
        Carro meuCarro = new Carro("Renault", "Logan", 2015);
        meuCarro.setAno(2012);
        
        System.out.println(meuCarro.getModelo());
        System.out.println(meuCarro.getAno());
        String marca = meuCarro.getMarca();
        System.out.println(marca);
        
        meuCarro.ligar();
        meuCarro.acelerar();
    }
}