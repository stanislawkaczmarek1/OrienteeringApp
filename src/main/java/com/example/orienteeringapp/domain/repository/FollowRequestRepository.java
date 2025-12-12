package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.FollowRequest;

import java.util.List;
import java.util.Optional;

public interface FollowRequestRepository {
    FollowRequest save(FollowRequest followRequest);
    void deleteById(Long id);
    Optional<FollowRequest> findById(Long id);
    List<FollowRequest> findPendingForTarget(Long targetId);
    FollowRequest acceptRequest(Long requestId);
    FollowRequest rejectRequest(Long requestId);
    FollowRequest findByRequesterAndTarget(Long requesterId, Long targetId);
}
