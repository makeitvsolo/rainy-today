package com.makeitvsolo.rainytoday.service.mapping;

import com.makeitvsolo.rainytoday.model.weather.Coordinates;
import com.makeitvsolo.rainytoday.service.dto.coordinate.CoordinatesDto;

public interface CoordinatesMapper {

    CoordinatesDto mapFrom(Coordinates coordinates);
}
