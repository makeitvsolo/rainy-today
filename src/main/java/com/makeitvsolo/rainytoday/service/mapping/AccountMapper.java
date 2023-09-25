package com.makeitvsolo.rainytoday.service.mapping;

import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.service.dto.account.AccountDto;

public interface AccountMapper {

    AccountDto mapFrom(Account account);
}
