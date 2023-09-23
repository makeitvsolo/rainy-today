package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.CoordinatesRepository;
import com.makeitvsolo.rainytoday.model.weather.Coordinates;
import com.makeitvsolo.rainytoday.service.dto.coordinate.CoordinatesDto;
import com.makeitvsolo.rainytoday.service.mapping.CoordinatesMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;
import static org.mockito.Mockito.*;

@DisplayName("CoordinatesService")
public class CoordinatesServiceTests {

    private CoordinatesService service;

    @Mock
    private CoordinatesRepository repository;

    @Mock
    private CoordinatesMapper mapper;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = openMocks(this);

        service = new CoordinatesService(repository, mapper);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Nested
    @DisplayName("search coordinates by name")
    public class SearchCoordinatesByName {

        private Coordinates existingCoordinates;
        private CoordinatesDto expectedCoordinates;

        @BeforeEach
        public void beforeEach() {
            existingCoordinates = new Coordinates("name", BigDecimal.ZERO, BigDecimal.ZERO);

            expectedCoordinates = new CoordinatesDto(
                    existingCoordinates.getLocation(),
                    existingCoordinates.getLatitude(),
                    existingCoordinates.getLongitude()
            );
        }

        @Test
        @DisplayName("gives all founded coordinates")
        public void givesAllFoundedCoordinates() {
            var locationName = "name";
            var expected = List.of(expectedCoordinates);

            when(repository.findByLocationName(locationName))
                    .thenReturn(List.of(existingCoordinates));
            when(mapper.mapFrom(existingCoordinates))
                    .thenReturn(expectedCoordinates);

            assertEquals(expected, service.searchCoordinatesByName(locationName));
        }
    }
}
