package br.edu.fatecpg.appviacep.view;

import br.edu.fatecpg.appviacep.model.Endereco;
import br.edu.fatecpg.appviacep.service.ConsomeApi;
import com.google.gson.Gson;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            String retorno = ConsomeApi.buscaCep();
            System.out.println(retorno);
            Endereco end = gson.fromJson(retorno, Endereco.class);
            System.out.println(end);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
