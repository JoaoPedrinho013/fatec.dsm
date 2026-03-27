package br.edu.fatecpg.open_meteo_clima.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaAtual {

    @JsonProperty("time")
    private String time;

    @JsonProperty("interval")
    private Integer interval;

    @JsonProperty("temperature_2m")
    private Double temperature_2m;

    @JsonProperty("windspeed_10m")
    private Double windspeed_10m;

    @JsonProperty("weathercode")
    private Integer weathercode;

    public String getDescricaoClima() {
        if (this.weathercode == null) return "Desconhecido";
        int code = this.weathercode;
        if (code == 0) return "Céu limpo";
        if (code <= 3) return "Parcialmente nublado";
        if (code <= 48) return "Neblina";
        if (code <= 67) return "Chuva";
        if (code <= 77) return "Neve";
        if (code <= 82) return "Pancadas de chuva";
        return "Desconhecido";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Double getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(Double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public Double getWindspeed_10m() {
        return windspeed_10m;
    }

    public void setWindspeed_10m(Double windspeed_10m) {
        this.windspeed_10m = windspeed_10m;
    }

    public Integer getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(Integer weathercode) {
        this.weathercode = weathercode;
    }
}