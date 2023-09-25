package com.makeitvsolo.rainytoday.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToAccountPrincipalMapper {

    public AccountPrincipal convert(DecodedJWT jwt) {
        return AccountPrincipal.builder()
                       .authorities(List.of())
                       .accountId(Long.parseLong(jwt.getSubject()))
                       .accountName(jwt.getClaim("account_name").asString())
                       .build();
    }
}
