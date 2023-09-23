package com.makeitvsolo.rainytoday.datasource.openweather.exception;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

public final class OpenWeatherException extends RainyTodayException {

    public OpenWeatherException(String message) {
        super(message);
    }

    public OpenWeatherException(Exception ex) {
        super(ex);
    }
}
