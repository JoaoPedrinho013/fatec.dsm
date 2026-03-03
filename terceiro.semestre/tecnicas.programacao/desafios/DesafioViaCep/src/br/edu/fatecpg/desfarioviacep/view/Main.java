package br.edu.fatecpg.desfarioviacep.view;

import br.edu.fatecpg.desfarioviacep.model.Endereco;
import br.edu.fatecpg.desfarioviacep.service.ConsomeApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<Endereco> enderecos = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        boolean validacao = true;

        while(validacao) {
            System.out.println("Seja bem vindo ao app de add ceps");
            System.out.println("Digite 1 para adcionar um CEP, \n" +
                    "Digite 2 para excluir um CEP \n" +
                    "Digite 3 para ver todos CEPs \n" +
                    "Digite 9 para sair:");

            int opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("ADD CEP");
                    System.out.println("Digite um CEP válido: ");
                    String cepDigitado = scan.nextLine();

                    try {
                        String retorno = ConsomeApi.buscaCep(cepDigitado);
                        Endereco end = gson.fromJson(retorno, Endereco.class);
                        enderecos.add(end);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    System.out.println("Excluir CEP");
                    break;
                case 3:
                    System.out.println("Listar todos CEPs");
                    System.out.println(enderecos);
                    break;
                case 9:
                    System.out.println("Obrigado por usar o sistema!");
                    validacao = false;
                    break;
            }




        }




       /* try {
            String retorno = ConsomeApi.buscaCep();
            System.out.println(retorno);
            Endereco end = gson.fromJson(retorno, Endereco.class);
            System.out.println(end);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        01001000
        11749440
        11749498
        */
    }
}