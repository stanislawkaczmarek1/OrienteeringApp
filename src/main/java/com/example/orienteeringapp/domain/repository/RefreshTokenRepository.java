package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByTokenWithLock(String token);
    void deleteByUserId(Long userId);
    void deleteById(Long id);
}
