package com.example.orienteeringapp.domain.model;

import com.example.orienteeringapp.domain.model.enums.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Post {
    private Long id;
    private Long userId;
    private String content;
    private Long mapId;
    private Long activityId;
    private Visibility visibility;
    private LocalDateTime createdAt;
}
