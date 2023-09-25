package com.makeitvsolo.rainytoday.model.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account")
public final class AccountTests {

    private Account account;

    @Nested
    @DisplayName("when favourites is empty")
    public class WhenFavouritesIsEmpty {

        private FavouriteLocation location;

        @BeforeEach
        public void beforeEach() {
            account = new Account("name", "passwd");
            location = new FavouriteLocation(0L, "name", BigDecimal.ZERO, BigDecimal.ZERO);
        }

        @Test
        @DisplayName("location can be added to favourites")
        public void locationCanBeAddedToFavourites() {
            assertTrue(account.addToFavourites(location));
            assertTrue(account.isInFavourites(location.getLatitude(), location.getLongitude()));
        }

        @Test
        @DisplayName("any location cannot be removed from favourites")
        public void anyLocationCannotBeRemovedFromFavourites() {
            var anyLocationId = 0L;

            assertFalse(account.removeFromFavourites(anyLocationId));
        }

        @Nested
        @DisplayName("when location added to favourites")
        public class WhenLocationAddedToFavourites {

            private FavouriteLocation existingLocation;

            @BeforeEach
            public void beforeEach() {
                existingLocation = new FavouriteLocation(0L, "name", BigDecimal.ZERO, BigDecimal.ZERO);

                account.addToFavourites(existingLocation);
            }

            @Test
            @DisplayName("it can be removed from favourites")
            public void itCanBeRemovedFromFavourites() {
                assertTrue(account.removeFromFavourites(existingLocation.getId()));
                assertFalse(account.isInFavourites(existingLocation.getLatitude(), existingLocation.getLongitude()));
            }

            @Test
            @DisplayName("same location cannot be added again")
            public void sameLocationCannotBeAddedAgain() {
                assertFalse(account.addToFavourites(existingLocation));
            }
        }
    }
}
