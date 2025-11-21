package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.repository.FollowRequestRepository;


public class FollowRequestService {
    private final FollowRequestRepository repository;

    public FollowRequestService(FollowRequestRepository repository) {
        this.repository = repository;
    }
}
