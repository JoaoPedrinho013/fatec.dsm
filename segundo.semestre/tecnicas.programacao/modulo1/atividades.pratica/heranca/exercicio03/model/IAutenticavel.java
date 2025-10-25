package exercicio03.model;

public interface IAutenticavel {

    boolean login(String usuario, String senha);
    void logout();
    
} 
