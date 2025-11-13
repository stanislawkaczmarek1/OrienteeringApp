package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.RefreshTokenEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUser(UserEntity user);
}
