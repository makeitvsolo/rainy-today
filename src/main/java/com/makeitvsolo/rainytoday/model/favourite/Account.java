package com.makeitvsolo.rainytoday.model.favourite;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
public final class Account {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String password;

    private Set<FavouriteLocation> favourites;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean addToFavourite(FavouriteLocation location) {
        return favourites.add(location);
    }

    public boolean removeFromFavourites(Long locationId) {
        return favourites.removeIf(location -> location.getId().equals(locationId));
    }

    public boolean isInFavourites(BigDecimal latitude, BigDecimal longitude) {
        return favourites.stream()
                       .anyMatch(location -> location.isSameAs(latitude, longitude));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account other)) {
            return false;
        }

        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
