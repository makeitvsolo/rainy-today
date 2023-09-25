package com.makeitvsolo.rainytoday.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public final class Coordinates {

    @Getter
    private String location;

    @Getter
    private BigDecimal latitude;

    @Getter
    private BigDecimal longitude;
}
