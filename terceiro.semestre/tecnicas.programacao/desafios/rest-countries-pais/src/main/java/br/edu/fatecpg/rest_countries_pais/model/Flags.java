package br.edu.fatecpg.rest_countries_pais.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flags {

    @JsonProperty("svg")
    private String svg;

    @JsonProperty("png")
    private String png;

    public String getSvg() { return svg; }
    public void setSvg(String svg) { this.svg = svg; }

    public String getPng() { return png; }
    public void setPng(String png) { this.png = png; }
}
