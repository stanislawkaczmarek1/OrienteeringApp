package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.FollowRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFollowRequestRepository extends JpaRepository<FollowRequestEntity, Long> {
}