package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserFollows {
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
