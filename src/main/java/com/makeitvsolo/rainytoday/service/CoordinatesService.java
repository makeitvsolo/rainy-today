package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.CoordinatesRepository;
import com.makeitvsolo.rainytoday.service.dto.coordinate.CoordinatesDto;
import com.makeitvsolo.rainytoday.service.mapping.CoordinatesMapper;

import java.util.List;

public final class CoordinatesService {

    private final CoordinatesRepository repository;
    private final CoordinatesMapper mapper;

    public CoordinatesService(CoordinatesRepository repository, CoordinatesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CoordinatesDto> searchCoordinatesByName(String locationName) {
        return repository.findByLocationName(locationName)
                       .stream()
                       .map(mapper::mapFrom)
                       .toList();
    }
}
