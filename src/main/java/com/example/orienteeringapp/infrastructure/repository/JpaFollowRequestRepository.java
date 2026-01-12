package com.example.orienteeringapp.infrastructure.repository;

import com.example.orienteeringapp.infrastructure.entity.FollowRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JpaFollowRequestRepository extends JpaRepository<FollowRequestEntity, Long> {
    List<FollowRequestEntity> findByTarget_Id(Long targetId);

    Optional<FollowRequestEntity> findByRequester_IdAndTarget_Id(Long requesterId, Long targetId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FollowRequestEntity f WHERE f.requester.id = :requesterId AND f.target.id = :targetId")
    void deleteByRequesterIdAndTargetId(@Param("requesterId") Long requesterId, @Param("targetId") Long targetId);
}