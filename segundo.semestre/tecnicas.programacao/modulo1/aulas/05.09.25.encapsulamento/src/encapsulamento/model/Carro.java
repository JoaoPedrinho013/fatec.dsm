package encapsulamento.model;


public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private double tanque;

    public Carro(String marca, String modelo, int ano) throws Exception{
        if(ano <= 2014){
            System.out.println("Não pode Criar!");
            throw new Exception();
        }else {
            this.ano = ano;
        }
        this.marca = marca;
        this.modelo = modelo;
        
    }

    public String getMarca(){
        return this.marca;
    }
    public void setMarca(String novaMarca){
        this.marca = novaMarca;
    }

    public String getModelo(){
        return this.modelo;
    }
    public void setModelo(String novoModelo){
        this.modelo = novoModelo;
    }

    public int getAno(){
        return this.ano;
    }
    public void setAno(int novoAno){
        if(novoAno <= 2014){
            System.out.println("O ano não pode ser alterado!");
        }else {
            this.ano = novoAno;
        }
    }

    public double getTanque(){
        return this.tanque;
    }
    public void setTanque(double novoTanque){
        this.tanque = novoTanque;
    }

    public void ligar(){
        System.out.println("Carro ligado!");
    }
    public void acelerar() {
        System.out.println("Acelerando....");
    }
}