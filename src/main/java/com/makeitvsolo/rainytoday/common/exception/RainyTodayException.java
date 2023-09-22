package com.makeitvsolo.rainytoday.common.exception;

public abstract class RainyTodayException extends RuntimeException {

    protected RainyTodayException(String message) {
        super(message);
    }

    protected RainyTodayException(Exception ex) {
        super(ex);
    }
}
