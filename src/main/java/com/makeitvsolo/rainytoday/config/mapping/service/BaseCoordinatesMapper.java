package com.makeitvsolo.rainytoday.config.mapping.service;

import com.makeitvsolo.rainytoday.model.weather.Coordinates;
import com.makeitvsolo.rainytoday.service.dto.coordinate.CoordinatesDto;
import com.makeitvsolo.rainytoday.service.mapping.CoordinatesMapper;

public final class BaseCoordinatesMapper implements CoordinatesMapper {

    @Override
    public CoordinatesDto mapFrom(Coordinates coordinates) {
        return new CoordinatesDto(
                coordinates.getLocation(),
                coordinates.getLatitude(),
                coordinates.getLongitude()
        );
    }
}
