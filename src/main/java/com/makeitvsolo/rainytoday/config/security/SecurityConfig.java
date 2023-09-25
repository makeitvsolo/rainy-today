package com.makeitvsolo.rainytoday.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter authFilter;
    private final AccountDetailsService detailsService;
    private final UnauthorizedHandler unauthorizedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter authFilter,
            AccountDetailsService detailsService,
            UnauthorizedHandler unauthorizedHandler
    ) {
        this.authFilter = authFilter;
        this.detailsService = detailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(FormLoginConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(
                        policy -> {
                            policy.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                        }
                )
                .securityMatcher("/api/v1/**")
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers("api/v1/access/**").permitAll();
                            registry.anyRequest().authenticated();
                        }
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder());

        return builder.build();
    }
}
