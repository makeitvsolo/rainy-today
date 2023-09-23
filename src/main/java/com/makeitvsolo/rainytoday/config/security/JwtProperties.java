package com.makeitvsolo.rainytoday.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Getter
@Setter
public class JwtProperties {

    private String secretKey;
    private Duration duration;
}
