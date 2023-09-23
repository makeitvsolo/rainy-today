package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.service.dto.account.AccountDto;
import com.makeitvsolo.rainytoday.service.dto.account.CreateAccountDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountAlreadyExistsException;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.mapping.AccountMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class AccountService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper mapper;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder, AccountMapper mapper) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public void createAccount(CreateAccountDto payload) {
        if (repository.existsByName(payload.getName())) {
            throw new AccountAlreadyExistsException(payload.getName());
        }

        var account = new Account(
                payload.getName(),
                passwordEncoder.encode(payload.getPassword())
        );

        repository.save(account);
    }

    public AccountDto getAccountByName(String name) {
        return repository.findByName(name)
                       .map(mapper::mapFrom)
                       .orElseThrow(() -> new AccountDoesNotExistsException(name));
    }
}
