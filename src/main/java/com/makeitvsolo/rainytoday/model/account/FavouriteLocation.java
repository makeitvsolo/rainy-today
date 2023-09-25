package com.makeitvsolo.rainytoday.model.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(
        name = "favourites",
        uniqueConstraints = @UniqueConstraint(columnNames = {"latitude", "longitude"}),
        indexes = @Index(columnList = "latitude, longitude", unique = true)
)
@AllArgsConstructor
@NoArgsConstructor
public final class FavouriteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(name = "latitude", nullable = false)
    @Getter
    @Setter
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    @Getter
    @Setter
    private BigDecimal longitude;

    public FavouriteLocation(String name, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean isSameAs(BigDecimal latitude, BigDecimal longitude) {
        return this.latitude.equals(latitude)
                       && this.longitude.equals(longitude);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FavouriteLocation other)) {
            return false;
        }

        return isSameAs(other.latitude, other.longitude);
    }

    @Override
    public int hashCode() {
        return latitude.hashCode() ^ longitude.hashCode();
    }
}
