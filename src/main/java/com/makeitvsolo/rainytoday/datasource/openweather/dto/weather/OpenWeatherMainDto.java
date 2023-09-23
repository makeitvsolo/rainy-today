package com.makeitvsolo.rainytoday.datasource.openweather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class OpenWeatherMainDto {

    @JsonProperty("temp")
    private double temperature;

    @JsonProperty("feels_like")
    private double feelsLike;

    @JsonProperty("temp_min")
    private double minTemperature;

    @JsonProperty("temp_max")
    private double maxTemperature;

    @JsonProperty("pressure")
    private int pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("sea_level")
    private int seaLevel;

    @JsonProperty("grnd_level")
    private int groundLevel;
}
