package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.application.dto.CreateMapDto;
import com.example.orienteeringapp.application.dto.CreateMapResponseDto;
import com.example.orienteeringapp.application.dto.MapDto;
import com.example.orienteeringapp.infrastructure.util.ControlPointMapper;
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
            toDomainMapData(dto.getMapData()),
            null
        );
        Map created = repository.save(map);
        return new CreateMapResponseDto(created.getId());
    }

    public Optional<MapDto> getMap(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public List<MapDto> getMapsByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public void deleteMap(Long id) {
        repository.delete(id);
    }

    public Optional<MapDto> updateMap(Long id, CreateMapDto dto) {
        return repository.findById(id).map(existingMap -> {
            Map updatedMap = new Map(
                    existingMap.getId(),
                    existingMap.getUserId(),
                    dto.getName(),
                    dto.getDescription(),
                    dto.getLocation(),
                    toDomainMapData(dto.getMapData()),
                    existingMap.getCreatedAt()
            );
            Map saved = repository.save(updatedMap);
            return toDto(saved);
        });
    }

    private MapDto toDto(Map map) {
        return new MapDto(
            map.getId(),
            map.getUserId(),
            map.getName(),
            map.getDescription(),
            map.getLocation(),
            toDtoMapData(map.getMapData()),
            map.getCreatedAt()
        );
    }

    private Map.MapData toDomainMapData(CreateMapDto.MapData dtoMapData) {
        if (dtoMapData == null) return new Map.MapData(List.of());
        return new Map.MapData(
            ControlPointMapper.mapList(dtoMapData.getControlPoints(), this::toDomainControlPoint)
        );
    }

    private Map.ControlPoint toDomainControlPoint(CreateMapDto.ControlPoint cp) {
        return new Map.ControlPoint(cp.getLatitude(), cp.getLongitude(), cp.getId());
    }

    private MapDto.MapData toDtoMapData(Map.MapData domainMapData) {
        if (domainMapData == null) return new MapDto.MapData(List.of());
        return new MapDto.MapData(
            ControlPointMapper.mapList(domainMapData.getControlPoints(), this::toDtoControlPoint)
        );
    }

    private MapDto.ControlPoint toDtoControlPoint(Map.ControlPoint cp) {
        return new MapDto.ControlPoint(cp.getLatitude(), cp.getLongitude(), cp.getId());
    }
}
