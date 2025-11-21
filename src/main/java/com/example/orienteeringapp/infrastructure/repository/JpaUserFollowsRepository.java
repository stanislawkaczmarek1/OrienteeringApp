package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.UserFollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserFollowsRepository extends JpaRepository<UserFollowsEntity, Long> {
}