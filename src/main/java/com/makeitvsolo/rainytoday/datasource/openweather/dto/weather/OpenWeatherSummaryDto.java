package com.makeitvsolo.rainytoday.datasource.openweather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class OpenWeatherSummaryDto {

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("description")
    private String description;

    @JsonProperty("main")
    private String main;

    @JsonProperty("id")
    private int id;
}
