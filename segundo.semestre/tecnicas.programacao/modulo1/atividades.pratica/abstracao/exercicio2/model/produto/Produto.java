package model.produto;
public class Produto {
    public String nome;
    public String categoria;
    public double preco;


    public void cadastrarProduto(String nome, String categoria, double preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        System.out.println("Produto cadastrado com sucesso!");
    }

    public void atualizarPreco(double novoPreco) {
        this.preco = novoPreco;
        System.out.println("Preço atualizado para: R$" + String.format("%.2f", novoPreco));
    }


    public void detalhes() {
        System.out.println("Nome: " + nome);
        System.out.println("Categoria: " + categoria);
        System.out.println("Preço: R$" + String.format("%.2f", preco));
    }

}
