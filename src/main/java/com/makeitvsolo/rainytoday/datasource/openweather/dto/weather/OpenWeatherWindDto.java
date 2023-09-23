package com.makeitvsolo.rainytoday.datasource.openweather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class OpenWeatherWindDto {

    @JsonProperty("speed")
    private double speed;

    @JsonProperty("deg")
    private int degree;

    @JsonProperty("gust")
    private double gust;
}
