package com.makeitvsolo.rainytoday.service.dto.favourite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class AddFavouriteLocationDto {

    private final Long accountId;
    private final String name;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
