package com.makeitvsolo.rainytoday.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account")
public class AccountTests {

    private Account account;

    @Test
    @DisplayName("creates with empty favourites")
    public void createsWithEmptyFavourites() {
        var name = "name";
        var password = "password";

        account = new Account(name, password);

        assertTrue(account.getAllFavourites().isEmpty());
    }

    @Nested
    @DisplayName("when favourites is empty")
    public class WhenFavouritesIsEmpty {

        private FavouriteLocation location;

        @BeforeEach
        public void beforeEach() {
            var name = "name";
            var password = "password";

            account = new Account(name, password);
            location = new FavouriteLocation("name", BigDecimal.ZERO, BigDecimal.ZERO);
        }

        @Test
        @DisplayName("location can be added to favourites")
        public void locationCanBeAddedToFavourites() {
            assertTrue(account.addToFavourites(location));
        }

        @Test
        @DisplayName("any location cannot be removed from favourites")
        public void anyLocationCannotBeRemovedFromFavourites() {
            assertFalse(account.removeFromFavourites(location));
        }

        @Nested
        @DisplayName("when location added to favourites")
        public class WhenLocationAddedToFavourites {

            private FavouriteLocation existingLocation;

            @BeforeEach
            public void beforeEach() {
                existingLocation = new FavouriteLocation("name", BigDecimal.ZERO, BigDecimal.ZERO);

                account.addToFavourites(existingLocation);
            }

            @Test
            @DisplayName("it can be removed from favourites")
            public void itCanBeRemovedFromFavourites() {
                assertTrue(account.removeFromFavourites(existingLocation));
            }

            @Test
            @DisplayName("same location cannot be added again")
            public void sameLocationCannotBeAddedAgain() {
                assertFalse(account.addToFavourites(existingLocation));
            }
        }
    }
}
