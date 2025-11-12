package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {}
