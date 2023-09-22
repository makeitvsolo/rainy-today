package com.makeitvsolo.rainytoday.model.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public final class Wind {

    @Getter
    private Double speed;

    @Getter
    private Integer directionInDegree;

    @Getter
    private Double gust;
}
