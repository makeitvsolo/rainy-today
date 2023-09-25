package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.AccountPrincipal;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.CoordinatesService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinates")
@Tag(name = "locations")
public class CoordinatesController {

    private final CoordinatesService service;

    public CoordinatesController(CoordinatesService service) {
        this.service = service;
    }

    @GetMapping("/location")
    @Operation(summary = "searches location by given name")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = """
                                            [
                                              {
                                                "name": "Ontario",
                                                "latitude": 34.065846,
                                                "longitude": -117.6484304
                                              },
                                              {
                                                "name": "Ontario",
                                                "latitude": 44.0265525,
                                                "longitude": -116.9629378
                                              },
                                              {
                                                "name": "Town of Ontario",
                                                "latitude": 43.2208968,
                                                "longitude": -77.2830421
                                              },
                                              {
                                                "name": "Ontario",
                                                "latitude": 40.7595418,
                                                "longitude": -82.5901658
                                              },
                                              {
                                                "name": "Ontario",
                                                "latitude": 43.7258037,
                                                "longitude": -90.59152
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
    public ResponseEntity<?> searchLocation(
            @RequestParam("name") String locationName,
            @AuthenticationPrincipal AccountPrincipal principal
            ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                           .body(service.searchCoordinatesByName(locationName));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
    }
}
