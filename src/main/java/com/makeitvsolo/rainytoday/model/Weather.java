package com.makeitvsolo.rainytoday.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@AllArgsConstructor
public final class Weather {

    @Getter
    private Integer id;

    @Getter
    private String summary;

    @Getter
    private String description;

    @Getter
    private ZonedDateTime dateTime;

    @Getter
    private BigDecimal latitude;

    @Getter
    private BigDecimal longitude;

    @Getter
    private Double temperature;

    @Getter
    private Integer pressure;

    @Getter
    private Integer humidity;

    @Getter
    private Integer visibility;
}
