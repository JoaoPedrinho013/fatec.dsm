package model;

import java.util.Arrays;

public class Supermercado {
    private String[] nomesProdutos;
    private double[] precos;
    private double[] descontos;

    public Supermercado(String[] nomesProdutos, double[] precos, double[] descontos) {
        this.nomesProdutos = nomesProdutos;
        this.precos = precos;
        this.descontos = descontos;
    }

    public void listarProdutos() {
        System.out.println("Produtos disponíveis:");
        for (int i = 0; i < nomesProdutos.length; i++) {
            double precoComDesconto = precos[i] * (1 - descontos[i] / 100);
            System.out.println(nomesProdutos[i] + " - Preço original: R$" 
                + String.format("%.2f", precos[i]) 
                + " | Preço com desconto: R$" 
                + String.format("%.2f", precoComDesconto));
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < precos.length; i++) {
            total += precos[i] * (1 - descontos[i] / 100);
        }
        return total;
    }

    public void produtoMaiorEconomia() {
        double maiorEconomia = 0;
        int indice = 0;
        for (int i = 0; i < precos.length; i++) {
            double economia = precos[i] * (descontos[i] / 100);
            if (economia > maiorEconomia) {
                maiorEconomia = economia;
                indice = i;
            }
        }
        System.out.println("Maior economia: " + nomesProdutos[indice] 
            + " | Economia de R$" + String.format("%.2f", maiorEconomia));
    }

    public void comprarProduto(String nome) {
        int index = -1;
        for (int i = 0; i < nomesProdutos.length; i++) {
            if (nomesProdutos[i].equalsIgnoreCase(nome)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Produto não encontrado!");
            return;
        }

        nomesProdutos = removerElemento(nomesProdutos, index);
        precos = removerElemento(precos, index);
        descontos = removerElemento(descontos, index);

        System.out.println("Produto comprado: " + nome);
    }

    public void reporProduto(String nome, double preco, double desconto) {
        nomesProdutos = adicionarElemento(nomesProdutos, nome);
        precos = adicionarElemento(precos, preco);
        descontos = adicionarElemento(descontos, desconto);

        System.out.println("Produto reposto: " + nome);
    }

    private String[] removerElemento(String[] array, int index) {
        String[] novoArray = new String[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                novoArray[j++] = array[i];
            }
        }
        return novoArray;
    }

    private double[] removerElemento(double[] array, int index) {
        double[] novoArray = new double[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != index) {
                novoArray[j++] = array[i];
            }
        }
        return novoArray;
    }

    private String[] adicionarElemento(String[] array, String novo) {
        String[] novoArray = Arrays.copyOf(array, array.length + 1);
        novoArray[array.length] = novo;
        return novoArray;
    }

    private double[] adicionarElemento(double[] array, double novo) {
        double[] novoArray = Arrays.copyOf(array, array.length + 1);
        novoArray[array.length] = novo;
        return novoArray;
    }
}
