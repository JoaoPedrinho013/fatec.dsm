package view;
import model.Funcionario;

public class Main {
    public static void main(String[] args) {
    
        Funcionario func1 = new Funcionario();
        func1.setNome("João");
        func1.setSalario(3549);
        func1.setCargo("Dev Backend");

        System.out.println("Nome do funcionário: " + func1.getNome());
        System.out.println("Salário do funcionário: R$" + func1.getSalario());
        System.out.println("Cargo do funcionário: " + func1.getCargo());
}
}