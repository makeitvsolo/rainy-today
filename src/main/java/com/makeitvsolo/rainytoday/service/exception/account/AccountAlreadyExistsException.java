package com.makeitvsolo.rainytoday.service.exception.account;

import com.makeitvsolo.rainytoday.common.exception.RainyTodayException;

import java.text.MessageFormat;

public final class AccountAlreadyExistsException extends RainyTodayException {

    public AccountAlreadyExistsException(String name) {
        super(MessageFormat.format("Account {0} already exists", name));
    }
}
