package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FollowRequest {
    private Long id;
    private Long requesterId;
    private Long targetId;
    private LocalDateTime createdAt;
}
