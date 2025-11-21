package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserFollowsResponseDto {
    private Long followerId;
    private Long followingId;
}
