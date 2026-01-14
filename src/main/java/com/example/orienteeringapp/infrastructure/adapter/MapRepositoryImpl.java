package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaMapRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import com.example.orienteeringapp.infrastructure.util.ControlPointMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MapRepositoryImpl implements MapRepository {

    private final JpaMapRepository jpaMapRepository;
    private final JpaUserRepository jpaUserRepository;

    public MapRepositoryImpl(JpaMapRepository jpaMapRepository, JpaUserRepository jpaUserRepository) {
        this.jpaMapRepository = jpaMapRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Map save(Map map) {
        MapEntity entity;

        if (map.getId() != null) {
            entity = jpaMapRepository.findById(map.getId())
                    .orElse(new MapEntity());
        } else {
            entity = new MapEntity();
        }

        UserEntity user = jpaUserRepository.findById(map.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(user);

        entity.setName(map.getName());
        entity.setDescription(map.getDescription());
        entity.setLocation(map.getLocation());
        entity.setMapData(toEntityMapData(map.getMapData()));

        MapEntity saved = jpaMapRepository.save(entity);
        return toDomainMap(saved);
    }

    @Override
    public Optional<Map> findById(Long id) {
        return jpaMapRepository.findById(id).map(this::toDomainMap);
    }

    @Override
    public List<Map> findByUserId(Long userId) {
        return jpaMapRepository.findByUserId(userId).stream()
                .map(this::toDomainMap)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaMapRepository.deleteById(id);
    }

    private Map toDomainMap(MapEntity entity) {
        return new Map(
                entity.getId(),
                entity.getUser().getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLocation(),
                toDomainMapData(entity.getMapData()),
                entity.getCreatedAt()
        );
    }

    private Map.MapData toDomainMapData(MapEntity.MapData entityMapData) {
        if (entityMapData == null) return new Map.MapData(List.of());
        return new Map.MapData(
                ControlPointMapper.mapList(entityMapData.getControlPoints(), this::toDomainControlPoint)
        );
    }

    private Map.ControlPoint toDomainControlPoint(MapEntity.ControlPoint cp) {
        return new Map.ControlPoint(cp.getLatitude(), cp.getLongitude(), cp.getId());
    }

    private MapEntity.MapData toEntityMapData(Map.MapData domainMapData) {
        MapEntity.MapData entityMapData = new MapEntity.MapData();
        if (domainMapData != null) {
            entityMapData.setControlPoints(
                    ControlPointMapper.mapList(domainMapData.getControlPoints(), this::toEntityControlPoint)
            );
        }
        return entityMapData;
    }

    private MapEntity.ControlPoint toEntityControlPoint(Map.ControlPoint cp) {
        MapEntity.ControlPoint entityCp = new MapEntity.ControlPoint();
        entityCp.setLatitude(cp.getLatitude());
        entityCp.setLongitude(cp.getLongitude());
        entityCp.setId(cp.getId());
        return entityCp;
    }
}
