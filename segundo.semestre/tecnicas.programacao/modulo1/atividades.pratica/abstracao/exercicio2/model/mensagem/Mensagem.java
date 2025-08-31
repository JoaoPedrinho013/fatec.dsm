package model.mensagem;

public class Mensagem {
    public String remetente, destinatario, conteudo;

    public void enviar() {
        System.out.println("Mensagem enviada para " + destinatario);
    }
    public void receber() {
        System.out.println("Mensagem recebida de " + remetente);
    }
    public void detalhes() {
        System.out.println("Remetente: " + remetente);
        System.out.println("Destinatario: " + destinatario);
        System.out.println("Conteudo: " + conteudo);
    }
}
