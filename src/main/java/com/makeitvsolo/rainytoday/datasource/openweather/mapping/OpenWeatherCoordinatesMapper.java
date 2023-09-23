package com.makeitvsolo.rainytoday.datasource.openweather.mapping;

import com.makeitvsolo.rainytoday.datasource.openweather.dto.coordinate.OpenWeatherCoordinatesDto;
import com.makeitvsolo.rainytoday.model.weather.Coordinates;

public interface OpenWeatherCoordinatesMapper {

    Coordinates mapFrom(OpenWeatherCoordinatesDto coordinates);
}
