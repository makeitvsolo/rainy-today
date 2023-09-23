package com.makeitvsolo.rainytoday.datasource;

import com.makeitvsolo.rainytoday.model.weather.Weather;

import java.math.BigDecimal;
import java.util.List;

public interface WeatherRepository {

    Weather findByCoordinates(BigDecimal latitude, BigDecimal longitude);
}
