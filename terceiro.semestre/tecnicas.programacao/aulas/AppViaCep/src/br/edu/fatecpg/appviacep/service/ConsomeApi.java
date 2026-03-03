package br.edu.fatecpg.appviacep.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsomeApi {

    public static String buscaCep() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/01001000/json/")).build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());




        return response.body();
    }

}
