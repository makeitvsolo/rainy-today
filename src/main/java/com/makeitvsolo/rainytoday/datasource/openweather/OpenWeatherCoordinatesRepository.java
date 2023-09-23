package com.makeitvsolo.rainytoday.datasource.openweather;

import com.makeitvsolo.rainytoday.datasource.CoordinatesRepository;
import com.makeitvsolo.rainytoday.datasource.openweather.dto.coordinate.OpenWeatherCoordinatesDto;
import com.makeitvsolo.rainytoday.datasource.openweather.exception.OpenWeatherException;
import com.makeitvsolo.rainytoday.datasource.openweather.mapping.OpenWeatherCoordinatesMapper;
import com.makeitvsolo.rainytoday.model.weather.Coordinates;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.List;

public final class OpenWeatherCoordinatesRepository implements CoordinatesRepository {

    private String baseUrl;
    private String appId;
    private final RestTemplate restTemplate;
    private final OpenWeatherCoordinatesMapper mapper;

    public OpenWeatherCoordinatesRepository(RestTemplate restTemplate, OpenWeatherCoordinatesMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<Coordinates> findByLocationName(String locationName) {
        try {
            var uri = UriComponentsBuilder.fromUriString(baseUrl + "geo/1.0/direct")
                              .queryParam("q", locationName)
                              .queryParam("limit", 10)
                              .queryParam("units", "metric")
                              .queryParam("appid", appId)
                              .build(false)
                              .toUri();

            var response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OpenWeatherCoordinatesDto>>() {
                    }
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new OpenWeatherException(
                        MessageFormat.format("Bad response status code {0}", response.getStatusCode())
                );
            }

            var responseBody = response.getBody();

            if (responseBody == null) {
                throw new OpenWeatherException("Empty response body");
            }

            return response.getBody()
                           .stream()
                           .map(mapper::mapFrom)
                           .toList();
        } catch (RestClientException ex) {
            throw new OpenWeatherException(ex);
        }
    }
}
