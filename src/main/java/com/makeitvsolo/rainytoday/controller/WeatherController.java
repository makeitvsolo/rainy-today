package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.AccountPrincipal;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.WeatherService;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> get(@AuthenticationPrincipal AccountPrincipal principal) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                           .body(service.getWeatherOnFavouriteLocations(principal.getAccountId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }
}
