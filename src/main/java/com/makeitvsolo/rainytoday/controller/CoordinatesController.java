package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.AccountPrincipal;
import com.makeitvsolo.rainytoday.service.CoordinatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coordinates")
public class CoordinatesController {

    private final CoordinatesService service;

    public CoordinatesController(CoordinatesService service) {
        this.service = service;
    }

    @GetMapping("/location")
    public ResponseEntity<?> searchLocation(
            @RequestParam("name") String locationName,
            @AuthenticationPrincipal AccountPrincipal principal
            ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                           .body(service.searchCoordinatesByName(locationName));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }
}
