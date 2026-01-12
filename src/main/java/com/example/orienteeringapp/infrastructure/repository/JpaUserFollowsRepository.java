package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.UserFollowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface JpaUserFollowsRepository extends JpaRepository<UserFollowsEntity, Long> {
    @Transactional
    @Modifying
    void deleteByFollower_IdAndFollowing_Id(Long followerId, Long followingId);
    boolean existsByFollower_IdAndFollowing_Id(Long followerId, Long followingId);

}