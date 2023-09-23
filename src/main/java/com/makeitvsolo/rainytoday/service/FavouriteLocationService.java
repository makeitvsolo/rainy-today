package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.model.account.FavouriteLocation;
import com.makeitvsolo.rainytoday.service.dto.favourite.AddFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.dto.favourite.RemoveFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.exception.favourite.AlreadyInFavouritesException;
import com.makeitvsolo.rainytoday.service.exception.favourite.NotInFavouritesException;

public final class FavouriteLocationService {

    private final AccountRepository repository;

    public FavouriteLocationService(AccountRepository repository) {
        this.repository = repository;
    }

    public void addLocationToFavourites(AddFavouriteLocationDto payload) {
        var account = repository.findById(payload.getAccountId())
                              .orElseThrow(AccountDoesNotExistsException::new);

        var location = new FavouriteLocation(
                payload.getName(),
                payload.getLatitude(),
                payload.getLongitude()
        );

        var success = account.addToFavourites(location);
        if (!success) {
            throw new AlreadyInFavouritesException(payload.getLatitude(), payload.getLongitude());
        }

        repository.save(account);
    }

    public void removeLocationFromFavourites(RemoveFavouriteLocationDto payload) {
        var account = repository.findById(payload.getAccountId())
                              .orElseThrow(AccountDoesNotExistsException::new);

        var success = account.removeFromFavourites(payload.getLocationId());
        if (!success) {
            throw new NotInFavouritesException();
        }

        repository.save(account);
    }
}
