package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.FollowRequest;

import java.util.List;
import java.util.Optional;

public interface FollowRequestRepository {
    FollowRequest save(FollowRequest followRequest);
    List<FollowRequest> findPendingForTarget(Long targetId);
    FollowRequest acceptRequest(Long requestId);
    FollowRequest rejectRequest(Long requestId);
    Optional<FollowRequest> findByRequesterAndTarget(Long requesterId, Long targetId);
    void deleteByRequesterAndTarget(Long requesterId, Long targetId);
}
