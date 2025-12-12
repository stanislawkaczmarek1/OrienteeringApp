package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.UserFollows;

import java.util.List;

public interface UserFollowsRepository {
    UserFollows save(UserFollows userFollows);
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    //List<UserFollows> findFollowing(Long followerId);
    //List<UserFollows> findFollowers(Long followingId);
}
