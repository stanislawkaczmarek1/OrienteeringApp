package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findByUser_Id(Long userId);

    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.user.id IN " +
            "(SELECT uf.following.id FROM UserFollowsEntity uf WHERE uf.follower.id = :userId) " +
            "OR p.user.id = :userId " +
            "ORDER BY p.createdAt DESC")
    List<PostEntity> findFeedForUser(@Param("userId") Long userId);
}
