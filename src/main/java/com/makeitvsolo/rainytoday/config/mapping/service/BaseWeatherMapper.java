package com.makeitvsolo.rainytoday.config.mapping.service;

import com.makeitvsolo.rainytoday.model.account.FavouriteLocation;
import com.makeitvsolo.rainytoday.model.weather.Weather;
import com.makeitvsolo.rainytoday.service.dto.weather.*;
import com.makeitvsolo.rainytoday.service.mapping.WeatherMapper;

public final class BaseWeatherMapper implements WeatherMapper {

    @Override
    public WeatherDto mapFrom(FavouriteLocation location, Weather weather) {
        return new WeatherDto(
                weather.getSummary(),
                weather.getDescription(),

                new LocationDto(
                        location.getId(),
                        location.getName(),
                        location.getLatitude(),
                        location.getLongitude()
                ),

                weather.getDateTime(),

                new TemperatureDto(
                        weather.getTemperature().getAverage(),
                        weather.getTemperature().getMin(),
                        weather.getTemperature().getMax()
                ),

                new PressureDto(
                        weather.getPressure().getSeaLevel(),
                        weather.getPressure().getGroundLevel()
                ),

                weather.getHumidity(),
                weather.getCloudiness(),

                new WindDto(
                        weather.getWind().getSpeed(),
                        weather.getWind().getDirectionInDegree(),
                        weather.getWind().getGust()
                )
        );
    }
}
