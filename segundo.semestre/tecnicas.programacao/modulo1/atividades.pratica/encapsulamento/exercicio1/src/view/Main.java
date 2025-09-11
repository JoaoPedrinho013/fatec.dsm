package view;

import model.Carro;

public class Main {
	public static void main(String[] args) {
		Carro meuCarro = new Carro("Renaut", "Logan", 2015);

        
        meuCarro.setCapacidadeTanque(30);
        meuCarro.setTanque(15);

        System.out.println("A capacidade total do Tanque é: " + meuCarro.getCapacidadeTanque());
        System.out.println("A quantidade no tanque é: " + meuCarro.getTanque());

        meuCarro.encherTanque(meuCarro.getCapacidadeTanque(), 15, meuCarro.getTanque(), 5);
        System.out.println("A quantidade no tanque agora é: " + meuCarro.getTanque());

        meuCarro.setModelo("Sandero");
        System.out.println(meuCarro.getModelo()); 

        int ano = meuCarro.getAno(); 

        System.out.println(ano);

        meuCarro.ligar();
        meuCarro.acelelar();
	}
}