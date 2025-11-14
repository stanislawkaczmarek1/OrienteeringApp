package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {
    Activity save(Activity activity);
    Optional<Activity> findById(Long id);
    List<Activity> findByUserId(Long userId);
    List<Activity> findByMapId(Long mapId);
    void delete(Long id);
}
