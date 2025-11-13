package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.Map;
import java.util.List;
import java.util.Optional;

public interface MapRepository {
    Map save(Map map);
    Optional<Map> findById(Long id);
    List<Map> findByUserId(Long userId);
    void delete(Long id);
}
