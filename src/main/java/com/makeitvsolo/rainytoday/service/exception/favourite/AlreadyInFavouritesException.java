package com.makeitvsolo.rainytoday.service.exception.favourite;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

import java.math.BigDecimal;
import java.text.MessageFormat;

public final class AlreadyInFavouritesException extends RainyTodayException {

    public AlreadyInFavouritesException(BigDecimal latitude, BigDecimal longitude) {
        super(MessageFormat.format(
                "Location with latitude {0} and longitude {1} already in favourites", latitude, longitude
        ));
    }
}
