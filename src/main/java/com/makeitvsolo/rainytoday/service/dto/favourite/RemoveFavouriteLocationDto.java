package com.makeitvsolo.rainytoday.service.dto.favourite;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class RemoveFavouriteLocationDto {

    private final Long accountId;
    private final Long locationId;
}
