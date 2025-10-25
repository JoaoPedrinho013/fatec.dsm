package exercicio03.model;

public class SistemaDeSeguranca implements IAutenticavel{
    
    private String usuario;
    private String senha;
    private boolean logado = false;

    public SistemaDeSeguranca(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }


    public boolean login(String usuario, String senha){
        if(this.usuario.equals(usuario) && this.senha.equals(senha)){
            logado = true;
            return logado;
        }
        else{
            return logado;
        }
    }

    public void logout(){
        if(logado == true){
            System.out.println("Sessão encerrada.");
            logado = false;
        }
        else{
            System.out.println("Você não está logado.");
        }
    }
}
