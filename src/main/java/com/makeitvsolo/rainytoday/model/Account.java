package com.makeitvsolo.rainytoday.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public final class Account {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String password;

    private Set<Location> favourites;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;

        this.favourites = new HashSet<>();
    }

    public boolean addToFavourites(Location location) {
        return favourites.add(location);
    }

    public boolean removeFromFavourites(Location location) {
        return favourites.remove(location);
    }

    public List<Location> getAllFavourites() {
        return favourites.stream()
                       .toList();
    }

    public Optional<Location> getFavouriteById(Long id) {
        return favourites.stream()
                       .filter(location -> location.getId().equals(id))
                       .findFirst();
    }

    public Optional<Location> getFavouriteByCoordinates(BigDecimal latitude, BigDecimal longitude) {
        return favourites.stream()
                       .filter(location -> location.isSameAs(latitude, longitude))
                       .findFirst();
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
