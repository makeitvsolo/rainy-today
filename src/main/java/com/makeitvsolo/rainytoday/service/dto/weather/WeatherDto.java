package com.makeitvsolo.rainytoday.service.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public final class WeatherDto {

    private final String summary;
    private final String description;
    private final LocationDto location;
    private final ZonedDateTime dateTime;
    private final TemperatureDto temperature;
    private final PressureDto pressure;
    private final Integer humidity;
    private final Integer cloudiness;
    private final WindDto wind;
}
