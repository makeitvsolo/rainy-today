package com.makeitvsolo.rainytoday.service.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class TemperatureDto {

    private final Double average;
    private final Double min;
    private final Double max;
}
