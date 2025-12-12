package com.example.orienteeringapp.application.dto;

import com.example.orienteeringapp.domain.model.enums.FollowRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowRequestResponseDto {
    private Long id;
    private Long requesterId;
    private Long targetId;
    private FollowRequestStatus status;
    private LocalDateTime createdAt;
}
