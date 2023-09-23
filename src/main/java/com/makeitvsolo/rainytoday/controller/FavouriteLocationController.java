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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteLocationController {

    private final FavouriteLocationService service;

    public FavouriteLocationController(FavouriteLocationService service) {
        this.service = service;
    }

    @PostMapping("/add")
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
        } catch (AccountDoesNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }

    @DeleteMapping("/{id}/remove")
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
        } catch (AccountDoesNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }
}
