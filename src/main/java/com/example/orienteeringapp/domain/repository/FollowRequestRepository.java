package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.FollowRequest;

public interface FollowRequestRepository {
    FollowRequest save(FollowRequest followRequest);
    void deleteById(Long id);
}
