package com.makeitvsolo.rainytoday.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationMinutes}")
    private Long expirationMinutes;
}
