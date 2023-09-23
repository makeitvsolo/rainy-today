package com.makeitvsolo.rainytoday.service.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class LocationDto {

    private final Long id;
    private final String location;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
