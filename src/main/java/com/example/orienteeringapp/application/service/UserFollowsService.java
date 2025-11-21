package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.repository.UserFollowsRepository;

public class UserFollowsService {
    private final UserFollowsRepository repository;

    public UserFollowsService(UserFollowsRepository repository) {
        this.repository = repository;
    }
}
