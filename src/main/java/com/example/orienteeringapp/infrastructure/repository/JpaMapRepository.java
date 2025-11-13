package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMapRepository extends JpaRepository<MapEntity, Long> {
    List<MapEntity> findByUserId(Long userId);
}
