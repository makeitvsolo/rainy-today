package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.AccountPrincipal;
import com.makeitvsolo.rainytoday.controller.request.favourite.AddFavouriteRequest;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.FavouriteLocationService;
import com.makeitvsolo.rainytoday.service.dto.favourite.AddFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.dto.favourite.RemoveFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.exception.favourite.AlreadyInFavouritesException;
import com.makeitvsolo.rainytoday.service.exception.favourite.NotInFavouritesException;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourites")
@Tag(name = "favourites")
public class FavouriteLocationController {

    private final FavouriteLocationService service;

    public FavouriteLocationController(FavouriteLocationService service) {
        this.service = service;
    }

    @PostMapping("/add")
    @Operation(summary = "adds given location to favourites")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "location added",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "location already exists in favourites",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
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
    public ResponseEntity<?> add(
            @RequestBody AddFavouriteRequest request,
            @AuthenticationPrincipal AccountPrincipal principal
    ) {
        try {
            var payload = new AddFavouriteLocationDto(
                    principal.getAccountId(),
                    request.getName(),
                    request.getLatitude(),
                    request.getLongitude()
            );

            service.addLocationToFavourites(payload);
            return ResponseEntity.status(HttpStatus.CREATED)
                           .build();
        } catch (AlreadyInFavouritesException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}/remove")
    @Operation(summary = "removes given location from favourites")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "location removed",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "location does not exists in favourites",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
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
    public ResponseEntity<?> remove(
            @PathVariable(name = "id") Long locationId,
            @AuthenticationPrincipal AccountPrincipal principal
    ) {
        try {
            var payload = new RemoveFavouriteLocationDto(
                    principal.getAccountId(),
                    locationId
            );

            service.removeLocationFromFavourites(payload);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                           .build();
        } catch (NotInFavouritesException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
    }
}
