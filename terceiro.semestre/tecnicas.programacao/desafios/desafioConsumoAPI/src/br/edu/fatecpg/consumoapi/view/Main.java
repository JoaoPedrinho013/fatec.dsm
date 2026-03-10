package br.edu.fatecpg.consumoapi.view;

import br.edu.fatecpg.consumoapi.service.BrasilApi;

public class Main {
    public static void main(String[] args) throws Exception {

        String cnpj = "10832644000108"; // coloque um CNPJ válido aqui

        String json = BrasilApi.buscaEmpresa(cnpj);

        System.out.println(
                json.replace(",", ",\n")
                        .replace("{", "{\n")
                        .replace("}", "\n}")
        );
    }
}
