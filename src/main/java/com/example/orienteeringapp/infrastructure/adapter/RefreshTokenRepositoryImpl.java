package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.RefreshToken;
import com.example.orienteeringapp.domain.repository.RefreshTokenRepository;
import com.example.orienteeringapp.infrastructure.entity.RefreshTokenEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaRefreshTokenRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final JpaUserRepository jpaUserRepository;

    public RefreshTokenRepositoryImpl(
            JpaRefreshTokenRepository jpaRefreshTokenRepository,
            JpaUserRepository jpaUserRepository
    ) {
        this.jpaRefreshTokenRepository = jpaRefreshTokenRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public RefreshToken save(RefreshToken token) {
        UserEntity user = jpaUserRepository.findById(token.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        RefreshTokenEntity entity = toEntity(token, user);
        RefreshTokenEntity saved = jpaRefreshTokenRepository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return jpaRefreshTokenRepository.findByToken(token)
                .map(this::toDomain);
    }

    @Override
    public void deleteByUserId(Long userId) {
        jpaUserRepository.findById(userId)
                .ifPresent(jpaRefreshTokenRepository::deleteByUser);
    }

    private RefreshTokenEntity toEntity(RefreshToken token, UserEntity user) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.setId(token.getId());
        entity.setUser(user);
        entity.setToken(token.getToken());
        entity.setExpiryDate(token.getExpiryDate());
        return entity;
    }

    private RefreshToken toDomain(RefreshTokenEntity entity) {
        return new RefreshToken(
                entity.getId(),
                entity.getUser().getId(),
                entity.getToken(),
                entity.getExpiryDate()
        );
    }
}
