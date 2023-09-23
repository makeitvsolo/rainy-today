package com.makeitvsolo.rainytoday.datasource.openweather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.TimeZone;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class OpenWeatherDto {

    @JsonProperty("coord")
    private OpenWeatherCoordDto coord;

    @JsonProperty("weather")
    private List<OpenWeatherSummaryDto> summary;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private OpenWeatherMainDto main;

    @JsonProperty("visibility")
    private int visibility;

    @JsonProperty("wind")
    private OpenWeatherWindDto wind;

    @JsonProperty("clouds")
    private OpenWeatherCloudsDto clouds;

    @JsonProperty("dt")
    private OffsetDateTime dateTime;

    @JsonProperty("sys")
    private OpenWeatherSysDto sys;

    @JsonProperty("timezone")
    private TimeZone timeZone;

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private int cod;
}
