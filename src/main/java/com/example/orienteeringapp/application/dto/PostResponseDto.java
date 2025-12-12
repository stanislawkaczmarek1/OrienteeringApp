package com.example.orienteeringapp.application.dto;

import com.example.orienteeringapp.domain.model.enums.PostVisibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private Long userId;
    private String content;
    private Long mapId;
    private Long activityId;
    private PostVisibility visibility;
    private LocalDateTime createdAt;
}
