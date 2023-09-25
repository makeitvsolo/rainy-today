package com.makeitvsolo.rainytoday.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Pressure {

    @Getter
    private Integer seaLevel;

    @Getter
    private Integer groundLevel;
}
