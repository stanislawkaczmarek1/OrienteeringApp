package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findByUserId(Long userId);
    List<ActivityEntity> findByMapId(Long mapId);
}
