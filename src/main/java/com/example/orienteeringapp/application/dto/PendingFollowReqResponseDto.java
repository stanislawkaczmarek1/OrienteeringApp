package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingFollowReqResponseDto {
    private Long id;
    private Long requesterId;
    private Long targetId;
    private LocalDateTime createdAt;
    private String requesterFullName;
    private String requesterUserName;
}
