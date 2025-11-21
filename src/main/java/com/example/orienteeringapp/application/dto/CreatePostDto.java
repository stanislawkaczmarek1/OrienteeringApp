package com.example.orienteeringapp.application.dto;

import com.example.orienteeringapp.domain.model.enums.PostVisibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostDto {
    private Long id;
    private Long userId;
    private String content;
    private Long mapId;
    private Long activityId;
    private PostVisibility visibility;
}
