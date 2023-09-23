package com.makeitvsolo.rainytoday.service.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class WindDto {

    private final Double speed;
    private final Integer direction;
    private final Double gust;
}
