package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.model.account.FavouriteLocation;
import com.makeitvsolo.rainytoday.service.dto.favourite.AddFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.dto.favourite.RemoveFavouriteLocationDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.exception.favourite.AlreadyInFavouritesException;
import com.makeitvsolo.rainytoday.service.exception.favourite.NotInFavouritesException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;


import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;
import static org.mockito.Mockito.*;

@DisplayName("FavouriteLocationService")
public final class FavouriteLocationServiceTests {

    private FavouriteLocationService service;

    @Mock
    private AccountRepository repository;

    @Mock
    private Account existingAccount;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = openMocks(this);

        service = new FavouriteLocationService(repository);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Nested
    @DisplayName("adds location to favourites")
    public class AddsLocationToFavourites {

        @Test
        @DisplayName("success when given account exists and location is not in favourites")
        public void successWhenGivenAccountExistsAndLocationIsNotInFavourites() {
            var accountId = 0L;
            var payload = new AddFavouriteLocationDto(accountId, "name", BigDecimal.ZERO, BigDecimal.ZERO);

            when(repository.findById(accountId))
                    .thenReturn(Optional.of(existingAccount));
            when(existingAccount.addToFavourites(
                    new FavouriteLocation(payload.getName(), payload.getLatitude(), payload.getLongitude())
            ))
                    .thenReturn(true);

            service.addLocationToFavourites(payload);

            verify(repository).save(existingAccount);
        }

        @Test
        @DisplayName("failure when given account does not exists")
        public void failureWhenGivenAccountDoesNotExists() {
            var accountId = 0L;
            var payload = new AddFavouriteLocationDto(accountId, "name", BigDecimal.ZERO, BigDecimal.ZERO);

            when(repository.findById(accountId))
                    .thenReturn(Optional.empty());

            assertThrows(AccountDoesNotExistsException.class, () -> service.addLocationToFavourites(payload));
        }

        @Test
        @DisplayName("failure when given location already in favourites")
        public void failureWhenGivenLocationAlreadyInFavourites() {
            var accountId = 0L;
            var payload = new AddFavouriteLocationDto(accountId, "name", BigDecimal.ZERO, BigDecimal.ZERO);

            when(repository.findById(accountId))
                    .thenReturn(Optional.of(existingAccount));
            when(existingAccount.addToFavourites(
                    new FavouriteLocation(payload.getName(), payload.getLatitude(), payload.getLongitude())
            ))
                    .thenReturn(false);

            assertThrows(AlreadyInFavouritesException.class, () -> service.addLocationToFavourites(payload));
        }
    }

    @Nested
    @DisplayName("removes location from favourites")
    public class RemovesLocationFromFavourites {

        @Test
        @DisplayName("success when given account exists and location is in favourites")
        public void successWhenGivenAccountExistsAndLocationIsInFavourites() {
            var accountId = 0L;
            var locationId = 0L;
            var payload = new RemoveFavouriteLocationDto(accountId, locationId);

            when(repository.findById(accountId))
                    .thenReturn(Optional.of(existingAccount));
            when(existingAccount.removeFromFavourites(locationId))
                    .thenReturn(true);

            service.removeLocationFromFavourites(payload);

            verify(repository).save(existingAccount);
        }

        @Test
        @DisplayName("failure when given account does not exists")
        public void failureWhenGivenAccountDoesNotExists() {
            var accountId = 0L;
            var locationId = 0L;
            var payload = new RemoveFavouriteLocationDto(accountId, locationId);

            when(repository.findById(accountId))
                    .thenReturn(Optional.empty());

            assertThrows(AccountDoesNotExistsException.class, () -> service.removeLocationFromFavourites(payload));
        }

        @Test
        @DisplayName("failure when given location does not in favourites")
        public void failureWhenGivenLocationDoesNotInFavourites() {
            var accountId = 0L;
            var locationId = 0L;
            var payload = new RemoveFavouriteLocationDto(accountId, locationId);

            when(repository.findById(accountId))
                    .thenReturn(Optional.of(existingAccount));
            when(existingAccount.removeFromFavourites(locationId))
                    .thenReturn(false);

            assertThrows(NotInFavouritesException.class, () -> service.removeLocationFromFavourites(payload));
        }
    }
}
