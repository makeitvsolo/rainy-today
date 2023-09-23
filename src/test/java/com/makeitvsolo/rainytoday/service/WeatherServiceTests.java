package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.datasource.WeatherRepository;
import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.model.account.FavouriteLocation;
import com.makeitvsolo.rainytoday.model.weather.*;
import com.makeitvsolo.rainytoday.service.dto.weather.*;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.mapping.WeatherMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;
import static org.mockito.Mockito.*;

@DisplayName("WeatherService")
public class WeatherServiceTests {

    private WeatherService service;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherMapper weatherMapper;

    @Mock
    private Account existingAccount;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = openMocks(this);

        service = new WeatherService(accountRepository, weatherRepository, weatherMapper);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Nested
    @DisplayName("gives weather on favourite locations")
    public class GivesWeatherOnFavouriteLocation {

        private FavouriteLocation existingLocation;
        private Weather weatherOnExistingLocation;
        private WeatherDto expectedWeather;

        @BeforeEach
        public void beforeEach() {
            existingLocation = new FavouriteLocation(0L, "name", BigDecimal.ZERO, BigDecimal.ZERO);

            weatherOnExistingLocation = new Weather(
                    "rainy today",
                    "is rain",
                    new Coordinates(
                            existingLocation.getName(),
                            existingLocation.getLatitude(),
                            existingLocation.getLongitude()
                    ),
                    ZonedDateTime.now(),
                    new Temperature(0.0, 0.0, 0.0),
                    new Pressure(0, 0),
                    0, 0,
                    new Wind(0.0, 0, 0.0)
            );

            expectedWeather = new WeatherDto(
                    weatherOnExistingLocation.getSummary(),
                    weatherOnExistingLocation.getDescription(),
                    new LocationDto(
                            existingLocation.getId(),
                            existingLocation.getName(),
                            existingLocation.getLatitude(),
                            existingLocation.getLongitude()
                    ),
                    weatherOnExistingLocation.getDateTime(),
                    new TemperatureDto(
                            weatherOnExistingLocation.getTemperature().getAverage(),
                            weatherOnExistingLocation.getTemperature().getMin(),
                            weatherOnExistingLocation.getTemperature().getMin()
                    ),
                    new PressureDto(
                            weatherOnExistingLocation.getPressure().getSeaLevel(),
                            weatherOnExistingLocation.getPressure().getGroundLevel()
                    ),
                    weatherOnExistingLocation.getHumidity(),
                    weatherOnExistingLocation.getCloudiness(),
                    new WindDto(
                            weatherOnExistingLocation.getWind().getSpeed(),
                            weatherOnExistingLocation.getWind().getDirectionInDegree(),
                            weatherOnExistingLocation.getWind().getGust()
                    )
            );
        }

        @Test
        @DisplayName("success when given account exists")
        public void successWhenGivenAccountExists() {
            var accountId = 0L;
            var expected = List.of(expectedWeather);

            when(accountRepository.findById(accountId))
                    .thenReturn(Optional.of(existingAccount));
            when(existingAccount.getAllFavourites())
                    .thenReturn(List.of(existingLocation));
            when(weatherRepository.findByCoordinates(existingLocation.getLatitude(), existingLocation.getLongitude()))
                    .thenReturn(weatherOnExistingLocation);
            when(weatherMapper.mapFrom(existingLocation, weatherOnExistingLocation))
                    .thenReturn(expectedWeather);

            assertEquals(expected, service.getWeatherOnFavouriteLocations(accountId));
        }

        @Test
        @DisplayName("failure when given account does not exists")
        public void failureWhenGivenAccountDoesNotExists() {
            var accountId = 0L;

            when(accountRepository.findById(accountId))
                    .thenReturn(Optional.empty());

            assertThrows(AccountDoesNotExistsException.class, () -> service.getWeatherOnFavouriteLocations(accountId));
        }
    }
}
