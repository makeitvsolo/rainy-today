package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.CoordinatesRepository;
import com.makeitvsolo.rainytoday.service.dto.coordinate.CoordinatesDto;
import com.makeitvsolo.rainytoday.service.mapping.CoordinatesMapper;

import java.util.List;

public final class SearchCoordinatesService {

    private final CoordinatesRepository repository;
    private final CoordinatesMapper mapper;

    public SearchCoordinatesService(CoordinatesRepository repository, CoordinatesMapper mapper) {
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
