package exercicio03.view;

import exercicio03.model.Produto;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIO 3: SOBRECARGA DE CONSTRUTORES ===\n");
        
        Produto produto1 = new Produto("Notebook");
        produto1.exibirInformacoes();
        
        Produto produto2 = new Produto("Mouse", 45.90);
        produto2.exibirInformacoes();
        
        Produto produto3 = new Produto("Teclado", 120.50, 15);
        produto3.exibirInformacoes();
        
        System.out.println("--- Explicação ---");
        System.out.println("A sobrecarga de construtores facilita a criação de objetos");
        System.out.println("com diferentes conjuntos de informações iniciais.");
        System.out.println("Isso proporciona flexibilidade na instanciação, permitindo");
        System.out.println("criar objetos com apenas os dados disponíveis no momento.");
    }
}