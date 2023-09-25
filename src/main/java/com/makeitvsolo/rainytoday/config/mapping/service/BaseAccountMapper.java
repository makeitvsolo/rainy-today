package com.makeitvsolo.rainytoday.config.mapping.service;

import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.service.dto.account.AccountDto;
import com.makeitvsolo.rainytoday.service.mapping.AccountMapper;

public final class BaseAccountMapper implements AccountMapper {

    @Override
    public AccountDto mapFrom(Account account) {
        return new AccountDto(
                account.getId(),
                account.getName()
        );
    }
}
