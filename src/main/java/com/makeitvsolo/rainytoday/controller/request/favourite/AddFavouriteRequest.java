package com.makeitvsolo.rainytoday.controller.request.favourite;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public final class AddFavouriteRequest {

    private final String name;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
}
