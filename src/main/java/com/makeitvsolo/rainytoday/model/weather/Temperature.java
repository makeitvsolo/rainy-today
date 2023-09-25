package com.makeitvsolo.rainytoday.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Temperature {

    @Getter
    private Double average;

    @Getter
    private Double min;

    @Getter
    private Double max;
}
