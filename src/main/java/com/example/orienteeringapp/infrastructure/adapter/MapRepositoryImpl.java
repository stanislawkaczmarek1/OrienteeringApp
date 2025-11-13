package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaMapRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MapRepositoryImpl implements MapRepository {

    private final JpaMapRepository jpaMapRepository;

    public MapRepositoryImpl(JpaMapRepository jpaMapRepository) {
        this.jpaMapRepository = jpaMapRepository;
    }

    @Override
    public Map save(Map map) {
        MapEntity entity = new MapEntity();

        entity.setId(map.getUserId());
        entity.setName(map.getName());
        entity.setDescription(map.getDescription());
        entity.setLocation(map.getLocation());
        entity.setMapData(map.getMapData());

        MapEntity saved = jpaMapRepository.save(entity);
        return mapEntityToDomain(saved);
    }

    @Override
    public Optional<Map> findById(Long id) {
        return jpaMapRepository.findById(id)
            .map(this::mapEntityToDomain);
    }

    @Override
    public List<Map> findByUserId(Long userId) {
        return jpaMapRepository.findByUserId(userId).stream()
            .map(this::mapEntityToDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaMapRepository.deleteById(id);
    }

    private Map mapEntityToDomain(MapEntity entity) {
        return new Map(
            entity.getId(),
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getLocation(),
            entity.getMapData(),
            entity.getCreatedAt()
        );
    }
}
