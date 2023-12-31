package com.makeitvsolo.rainytoday.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtEncoder {

    private final JwtProperties properties;

    public JwtEncoder(JwtProperties properties) {
        this.properties = properties;
    }

    public String encode(Long accountId, String accountName) {
        var now = Instant.now();

        return JWT.create()
                       .withSubject(String.valueOf(accountId))
                       .withIssuedAt(now)
                       .withExpiresAt(now.plus(Duration.of(properties.getExpirationMinutes(), ChronoUnit.MINUTES)))
                       .withClaim("account_name", accountName)
                       .sign(Algorithm.HMAC256(properties.getSecretKey()));
    }
}
