package com.makeitvsolo.rainytoday.service.exception.account;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

public final class AccountDoesNotExistsException extends RainyTodayException {

    public AccountDoesNotExistsException() {
        super("Account is missing");
    }
}
