package com.makeitvsolo.rainytoday.model.favourite;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public final class FavouriteLocation {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private BigDecimal latitude;

    @Getter
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
