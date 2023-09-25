package com.makeitvsolo.rainytoday.config.security;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class AccountDetailsService implements UserDetailsService {

    private final AccountRepository repository;

    public AccountDetailsService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username)
                       .map(account -> {
                           return AccountPrincipal.builder()
                                          .accountId(account.getId())
                                          .accountName(account.getName())
                                          .password(account.getPassword())
                                          .authorities(List.of())
                                          .build();
                       })
                       .orElseThrow();
    }
}
