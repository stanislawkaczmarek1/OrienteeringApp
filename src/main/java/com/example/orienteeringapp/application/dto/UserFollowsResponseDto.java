package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowsResponseDto {
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
