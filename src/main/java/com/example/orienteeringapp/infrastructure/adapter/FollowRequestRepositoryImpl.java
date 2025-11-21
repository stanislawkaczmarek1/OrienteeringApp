package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaFollowRequestRepository;
import org.springframework.stereotype.Component;

@Component
public class FollowRequestRepositoryImpl implements FollowRequestRepository {
    private final JpaFollowRequestRepository jpaFollowRequestRepository;

    public FollowRequestRepositoryImpl(JpaFollowRequestRepository jpaFollowRequestRepository) {
        this.jpaFollowRequestRepository = jpaFollowRequestRepository;
    }
}
