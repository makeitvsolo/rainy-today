package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.datasource.WeatherRepository;
import com.makeitvsolo.rainytoday.service.dto.weather.WeatherDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.mapping.WeatherMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class WeatherService {

    private final AccountRepository accountRepository;
    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherService(
            AccountRepository accountRepository,
            WeatherRepository weatherRepository,
            WeatherMapper weatherMapper
    ) {
        this.accountRepository = accountRepository;
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    public List<WeatherDto> getWeatherOnFavouriteLocations(Long accountId) {
        var account = accountRepository.findById(accountId)
                              .orElseThrow(AccountDoesNotExistsException::new);

        return account.getAllFavourites()
                       .stream()
                       .map(favourite -> {
                           var weather = weatherRepository.findByCoordinates(
                                   favourite.getLatitude(),
                                   favourite.getLongitude()
                           );

                            return weatherMapper.mapFrom(favourite, weather);
                       })
                       .toList();
    }
}
