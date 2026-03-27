package br.edu.fatecpg.open_meteo_clima.service;

import br.edu.fatecpg.open_meteo_clima.model.ClimaDados;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenMeteoClimaService {

    @Value("${openmeteo.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClimaDados buscarDados(Double latitude, Double longitude) {
        try {
            String json = restTemplate.getForObject(apiUrl, String.class, latitude, longitude);
            return objectMapper.readValue(json, ClimaDados.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar dados climáticos", e);
        }
    }
}
