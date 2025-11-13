package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.application.dto.CreateMapDto;
import com.example.orienteeringapp.application.dto.CreateMapResponseDto;
import com.example.orienteeringapp.application.dto.MapDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MapService {

    private final MapRepository repository;

    public MapService(MapRepository repository) {
        this.repository = repository;
    }

    public CreateMapResponseDto createMap(CreateMapDto dto) {
        Map map = new Map(
                null,
                dto.getUserId(),
                dto.getName(),
                dto.getDescription(),
                dto.getLocation(),
                dto.getMapData(),
                null
        );
        Map created = repository.save(map);
        return new CreateMapResponseDto(created.getId());
    }

    public Optional<MapDto> getMap(Long id) {
        return repository.findById(id)
                .map(this::mapToDto);
    }

    public List<MapDto> getMapsByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteMap(Long id) {
        repository.delete(id);
    }

    private MapDto mapToDto(Map map) {
        return new MapDto(
                map.getId(),
                map.getUserId(),
                map.getName(),
                map.getDescription(),
                map.getLocation(),
                map.getMapData(),
                map.getCreatedAt()
        );
    }
}
