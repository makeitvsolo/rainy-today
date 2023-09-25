package com.makeitvsolo.rainytoday.service.dto.coordinate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class CoordinatesDto {

    private final String name;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
