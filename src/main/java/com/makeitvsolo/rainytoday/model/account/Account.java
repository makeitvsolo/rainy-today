package com.makeitvsolo.rainytoday.model.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts", indexes = @Index(columnList = "name", unique = true))
@AllArgsConstructor
@NoArgsConstructor
public final class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "accounts_favourites",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "favourite_id", referencedColumnName = "id")
    )
    @Setter
    private Set<FavouriteLocation> favourites;

    public Account(String name, String password) {
        this.name = name;
        this.password = password;

        this.favourites = new HashSet<>();
    }

    public boolean addToFavourites(FavouriteLocation location) {
        return favourites.add(location);
    }

    public boolean removeFromFavourites(Long locationId) {
        return favourites.removeIf(location -> location.getId().equals(locationId));
    }

    public boolean isInFavourites(BigDecimal latitude, BigDecimal longitude) {
        return favourites.stream()
                       .anyMatch(location -> location.isSameAs(latitude, longitude));
    }

    public List<FavouriteLocation> getAllFavourites() {
        return favourites.stream()
                       .toList();
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
