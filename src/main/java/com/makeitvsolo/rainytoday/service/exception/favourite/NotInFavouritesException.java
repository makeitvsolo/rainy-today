package com.makeitvsolo.rainytoday.service.exception.favourite;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

public final class NotInFavouritesException extends RainyTodayException {

    public NotInFavouritesException() {
        super("Location is not in favourites");
    }
}
