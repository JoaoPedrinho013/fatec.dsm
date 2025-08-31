package model.pedido;

public class Pedido {
    public String codigoPedido;
    public String data;
    public double valorTotal;
    public String cliente;
    public String itens ;

    public void adicionarItem(String item) {
        System.out.println("Item " + item + " adicionado ao pedido " + codigoPedido);
        itens += (itens.isEmpty() ? "" : ", " + item);
    }
    public void removerItem(String item) {
        System.out.println("Item " + item + " removido do pedido " + codigoPedido);
        itens = itens.replace(item, "").replaceAll(", ,", ",").replaceAll("^, |, $", "").trim();
    }
    public void calcularValorTotal() {
        System.out.println("Calculando valor total do pedido " + codigoPedido);
    }
    public void detalhes() {
        System.out.println("Detalhes do Pedido:");
        System.out.println("Código do Pedido: " + codigoPedido);
        System.out.println("Data: " + data);
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Cliente: " + cliente);
        System.out.println("Itens: " + itens);
    }
}
