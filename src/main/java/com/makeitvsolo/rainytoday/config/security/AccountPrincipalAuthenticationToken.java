package com.makeitvsolo.rainytoday.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AccountPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final AccountPrincipal principal;

    public AccountPrincipalAuthenticationToken(AccountPrincipal principal) {
        super(principal.getAuthorities());

        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
