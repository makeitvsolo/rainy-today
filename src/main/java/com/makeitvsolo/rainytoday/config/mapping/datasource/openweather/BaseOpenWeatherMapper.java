package com.makeitvsolo.rainytoday.config.mapping.datasource.openweather;

import com.makeitvsolo.rainytoday.datasource.openweather.dto.weather.OpenWeatherDto;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherMapper;
import com.makeitvsolo.rainytoday.model.weather.*;

public final class BaseOpenWeatherMapper implements OpenWeatherMapper {

    @Override
    public Weather mapFrom(OpenWeatherDto weather) {
        return new Weather(
                weather.getSummary().get(0).getMain(),
                weather.getSummary().get(0).getDescription(),

                new Coordinates(
                        weather.getName(),
                        weather.getCoord().getLatitude(),
                        weather.getCoord().getLongitude()
                ),

                weather.getDateTime()
                        .atZoneSameInstant(weather.getTimeZone().toZoneId()),

                new Temperature(
                        weather.getMain().getTemperature(),
                        weather.getMain().getMinTemperature(),
                        weather.getMain().getMaxTemperature()
                ),

                new Pressure(
                        weather.getMain().getSeaLevel(),
                        weather.getMain().getGroundLevel()
                ),

                weather.getMain().getHumidity(),
                weather.getClouds().getPercent(),

                new Wind(
                        weather.getWind().getSpeed(),
                        weather.getWind().getDegree(),
                        weather.getWind().getGust()
                )
        );
    }
}
