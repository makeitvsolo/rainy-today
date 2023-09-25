package com.makeitvsolo.rainytoday.service.exception.account;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

import java.text.MessageFormat;

public final class AccountDoesNotExistsException extends RainyTodayException {

    public AccountDoesNotExistsException() {
        super("Account is missing");
    }

    public AccountDoesNotExistsException(String name) {
        super(MessageFormat.format("Account {0} does not exists", name));
    }
}
