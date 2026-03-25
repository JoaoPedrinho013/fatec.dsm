package br.edu.fatecpg.rest_countries_pais.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pais {

    @JsonProperty("name")
    private Nome nome;

    // BUG FIX #2: A API retorna capital como array, ex: ["Brasília"]
    @JsonProperty("capital")
    private List<String> capital;

    @JsonProperty("region")
    private String regiao;

    @JsonProperty("subregion")
    private String subRegiao;

    // BUG FIX #3: A API retorna population como número inteiro, não String
    @JsonProperty("population")
    private long populacao;

    // BUG FIX #3: A API retorna area como número decimal, não String
    @JsonProperty("area")
    private double area;

    @JsonProperty("fifa")
    private String sigla;

    @JsonProperty("flags")
    private Flags bandeiras;

    @JsonProperty("Response")
    private String response;

    @JsonProperty("Error")
    private String error;

    public Nome getNome() { return nome; }
    public void setNome(Nome nome) { this.nome = nome; }

    public String getCapital() {
        if (capital != null && !capital.isEmpty()) return capital.get(0);
        return "N/A";
    }
    public void setCapital(List<String> capital) { this.capital = capital; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getSubRegiao() { return subRegiao; }
    public void setSubRegiao(String subRegiao) { this.subRegiao = subRegiao; }

    public long getPopulacao() { return populacao; }
    public void setPopulacao(long populacao) { this.populacao = populacao; }

    public double getArea() { return area; }
    public void setArea(double area) { this.area = area; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public Flags getBandeiras() { return bandeiras; }
    public void setBandeiras(Flags bandeiras) { this.bandeiras = bandeiras; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
