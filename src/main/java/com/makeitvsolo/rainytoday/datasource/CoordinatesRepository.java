package com.makeitvsolo.rainytoday.datasource;

import com.makeitvsolo.rainytoday.model.weather.Coordinates;

import java.util.List;

public interface CoordinatesRepository {

    List<Coordinates> findByLocationName(String locationName);
}
