package com.example.orienteeringapp.domain.model;

import com.example.orienteeringapp.domain.model.enums.FollowRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FollowRequest {
    private Long id;
    private Long requesterId;
    private Long targetId;
    private FollowRequestStatus status;
    private LocalDateTime createdAt;
}
