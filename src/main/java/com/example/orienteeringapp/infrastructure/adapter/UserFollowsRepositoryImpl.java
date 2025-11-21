package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserFollowsRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFollowsRepositoryImpl implements UserFollowsRepository {
    private final JpaUserFollowsRepository jpaUserFollowsRepository;

    public UserFollowsRepositoryImpl(JpaUserFollowsRepository jpaUserFollowsRepository) {
        this.jpaUserFollowsRepository = jpaUserFollowsRepository;
    }
}
