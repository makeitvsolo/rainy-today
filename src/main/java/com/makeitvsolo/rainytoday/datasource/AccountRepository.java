package com.makeitvsolo.rainytoday.datasource;

import com.makeitvsolo.rainytoday.model.account.Account;

import java.util.Optional;

public interface AccountRepository {

    void save(Account account);

    Optional<Account> findById(Long id);
}
