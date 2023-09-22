package com.makeitvsolo.rainytoday.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
public final class Weather {

    @Getter
    private String summary;

    @Getter
    private String description;

    @Getter
    private Coordinates coordinates;

    @Getter
    private ZonedDateTime dateTime;

    @Getter
    private Temperature temperature;

    @Getter
    private Pressure pressure;

    @Getter
    private Integer humidity;

    @Getter
    private Integer cloudiness;

    @Getter
    private Wind wind;
}
