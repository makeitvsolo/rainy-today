package com.makeitvsolo.rainytoday.config.mapping.datasource.openweather;

import com.makeitvsolo.rainytoday.datasource.openweather.dto.coordinate.OpenWeatherCoordinatesDto;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherCoordinatesMapper;
import com.makeitvsolo.rainytoday.model.weather.Coordinates;

public final class BaseOpenWeatherCoordinatesMapper implements OpenWeatherCoordinatesMapper {

    @Override
    public Coordinates mapFrom(OpenWeatherCoordinatesDto coordinates) {
        return new Coordinates(
                coordinates.getName(),
                coordinates.getLatitude(),
                coordinates.getLongitude()
        );
    }
}
