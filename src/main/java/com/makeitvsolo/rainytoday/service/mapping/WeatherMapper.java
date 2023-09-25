package com.makeitvsolo.rainytoday.service.mapping;

import com.makeitvsolo.rainytoday.model.account.FavouriteLocation;
import com.makeitvsolo.rainytoday.model.weather.Weather;
import com.makeitvsolo.rainytoday.service.dto.weather.WeatherDto;

public interface WeatherMapper {

    WeatherDto mapFrom(FavouriteLocation location, Weather weather);
}
