package com.makeitvsolo.rainytoday.datasource.openweather.mapping;

import com.makeitvsolo.rainytoday.datasource.openweather.dto.weather.OpenWeatherDto;
import com.makeitvsolo.rainytoday.model.weather.Weather;

public interface OpenWeatherMapper {

    Weather mapFrom(OpenWeatherDto weather);
}
