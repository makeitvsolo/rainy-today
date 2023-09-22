package com.makeitvsolo.rainytoday.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public final class FavouriteLocation {

    private static final int DEFAULT_SCALE = 2;

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
        return this.latitude.setScale(DEFAULT_SCALE, RoundingMode.HALF_EVEN)
                       .compareTo(latitude.setScale(DEFAULT_SCALE, RoundingMode.HALF_EVEN)) == 0
                && this.longitude.setScale(DEFAULT_SCALE, RoundingMode.HALF_EVEN)
                           .compareTo(longitude.setScale(DEFAULT_SCALE, RoundingMode.HALF_EVEN)) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FavouriteLocation other)) {
            return false;
        }

        return other.isSameAs(latitude, longitude);
    }

    @Override
    public int hashCode() {
        return latitude.hashCode() ^ longitude.hashCode();
    }
}
