package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.UserFollows;


public interface UserFollowsRepository {
    UserFollows save(UserFollows userFollows);
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
}
