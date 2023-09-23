package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.WeatherService;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                           .body(service.getWeatherOnFavouriteLocations());
        } catch (AccountDoesNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }
}
