package br.edu.fatecpg.rest_countries_pais.service;

import br.edu.fatecpg.rest_countries_pais.model.Pais;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCountriesService {

    @Value("${restcountries.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Pais buscarPorPais(String pais) {
        String url = apiUrl + pais;
        try {
            String json = restTemplate.getForObject(url, String.class);

            Pais[] resultado = objectMapper.readValue(json, Pais[].class);

            if (resultado == null || resultado.length == 0) {
                Pais erro = new Pais();
                erro.setResponse("False");
                erro.setError("Nenhum país encontrado para: " + pais);
                return erro;
            }

            return resultado[0];

        } catch (Exception e) {
            Pais erro = new Pais();
            erro.setResponse("False");
            erro.setError("País não encontrado. Verifique o nome e tente novamente.");
            return erro;
        }
    }
}
