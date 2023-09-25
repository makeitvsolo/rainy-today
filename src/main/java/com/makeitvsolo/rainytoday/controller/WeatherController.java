package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.AccountPrincipal;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@Tag(name = "weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "gives weather on all favourite locations")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "weather on favourite locations",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = """
                                            [
                                              {
                                                "summary": "Haze",
                                                "description": "haze",
                                                "location": {
                                                  "id": 1,
                                                  "location": "Ontario",
                                                  "latitude": 34.07,
                                                  "longitude": -117.65
                                                },
                                                "dateTime": "2023-09-25T15:05:04Z",
                                                "temperature": {
                                                  "average": 17,
                                                  "min": 12.99,
                                                  "max": 22.98
                                                },
                                                "pressure": {
                                                  "seaLevel": 0,
                                                  "groundLevel": 0
                                                },
                                                "humidity": 79,
                                                "cloudiness": 0,
                                                "wind": {
                                                  "speed": 3.09,
                                                  "direction": 250,
                                                  "gust": 0
                                                }
                                              }
                                            ]
                                            """)}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    )
            }
    )
    public ResponseEntity<?> get(@AuthenticationPrincipal AccountPrincipal principal) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                           .body(service.getWeatherOnFavouriteLocations(principal.getAccountId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
    }
}
