package com.makeitvsolo.rainytoday.config.mapping;

import com.makeitvsolo.rainytoday.config.mapping.datasource.openweather.BaseOpenWeatherCoordinatesMapper;
import com.makeitvsolo.rainytoday.config.mapping.datasource.openweather.BaseOpenWeatherMapper;
import com.makeitvsolo.rainytoday.config.mapping.service.BaseAccountMapper;
import com.makeitvsolo.rainytoday.config.mapping.service.BaseCoordinatesMapper;
import com.makeitvsolo.rainytoday.config.mapping.service.BaseWeatherMapper;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherCoordinatesMapper;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherMapper;
import com.makeitvsolo.rainytoday.service.mapping.AccountMapper;
import com.makeitvsolo.rainytoday.service.mapping.CoordinatesMapper;
import com.makeitvsolo.rainytoday.service.mapping.WeatherMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public AccountMapper accountMapper() {
        return new BaseAccountMapper();
    }

    @Bean
    public CoordinatesMapper coordinatesMapper() {
        return new BaseCoordinatesMapper();
    }

    @Bean
    public WeatherMapper weatherMapper() {
        return new BaseWeatherMapper();
    }

    @Bean
    public OpenWeatherCoordinatesMapper openWeatherCoordinatesMapper() {
        return new BaseOpenWeatherCoordinatesMapper();
    }

    @Bean
    public OpenWeatherMapper openWeatherMapper() {
        return new BaseOpenWeatherMapper();
    }
}
