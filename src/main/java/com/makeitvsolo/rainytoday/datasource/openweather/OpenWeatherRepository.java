package com.makeitvsolo.rainytoday.datasource.openweather;

import com.makeitvsolo.rainytoday.datasource.WeatherRepository;
import com.makeitvsolo.rainytoday.datasource.openweather.dto.weather.OpenWeatherDto;
import com.makeitvsolo.rainytoday.datasource.openweather.exception.OpenWeatherException;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherMapper;
import com.makeitvsolo.rainytoday.model.weather.Weather;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Component
public final class OpenWeatherRepository implements WeatherRepository {

    private String baseUrl;
    private String appId;
    private final RestTemplate restTemplate;
    private final OpenWeatherMapper mapper;

    public OpenWeatherRepository(RestTemplate restTemplate, OpenWeatherMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public Weather findByCoordinates(BigDecimal latitude, BigDecimal longitude) {
        try {
            var uri = UriComponentsBuilder
                              .fromUriString(baseUrl + "data/2.5/weather")
                              .queryParam("lat", latitude)
                              .queryParam("lon", longitude)
                              .queryParam("units", "metric")
                              .queryParam("appid", appId)
                              .build(false)
                              .toUri();

            var response = restTemplate.getForEntity(uri, OpenWeatherDto.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new OpenWeatherException(
                        MessageFormat.format("Bad response status code {0}", response.getStatusCode())
                );
            }

            var responseBody = response.getBody();

            if (responseBody == null) {
                throw new OpenWeatherException("Empty response body");
            }

            return mapper.mapFrom(responseBody);
        } catch (RestClientException ex) {
            throw new OpenWeatherException(ex);
        }
    }
}
