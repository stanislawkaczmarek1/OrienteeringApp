package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
}
