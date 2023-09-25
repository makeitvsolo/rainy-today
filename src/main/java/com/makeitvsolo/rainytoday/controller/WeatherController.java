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
                                    examples = {@ExampleObject(value = "{[{\n" +
                                                                               "    \"summary\": \"Clouds\",\n" +
                                                                               "    \"description\": \"broken clouds\",\n" +
                                                                               "    \"location\": {\n" +
                                                                               "      \"id\": 1,\n" +
                                                                               "      \"location\": \"Omsk\",\n" +
                                                                               "      \"latitude\": 54.99,\n" +
                                                                               "      \"longitude\": 73.37\n" +
                                                                               "    },\n" +
                                                                               "    \"dateTime\": \"2023-09-25T14:12:09Z\",\n" +
                                                                               "    \"temperature\": {\n" +
                                                                               "      \"average\": 15.55,\n" +
                                                                               "      \"min\": 15.55,\n" +
                                                                               "      \"max\": 15.99\n" +
                                                                               "    },\n" +
                                                                               "    \"pressure\": {\n" +
                                                                               "      \"seaLevel\": 0,\n" +
                                                                               "      \"groundLevel\": 0\n" +
                                                                               "    },\n" +
                                                                               "    \"humidity\": 67,\n" +
                                                                               "    \"cloudiness\": 75,\n" +
                                                                               "    \"wind\": {\n" +
                                                                               "      \"speed\": 1,\n" +
                                                                               "      \"direction\": 210,\n" +
                                                                               "      \"gust\": 0\n" +
                                                                               "    }\n" +
                                                                               "  },]}")}
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
