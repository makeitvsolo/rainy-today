package com.makeitvsolo.rainytoday.service.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class PressureDto {

    private final Integer seaLevel;
    private final Integer groundLevel;
}
