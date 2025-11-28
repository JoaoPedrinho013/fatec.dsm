package SistemaBancario.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    
    // CONSTRUTOR
    public Cliente(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    // TO STRING
    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf + " | Tel: " + telefone;
    }
}
