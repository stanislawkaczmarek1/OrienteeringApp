package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String location;
    private java.util.Map<String, Object> mapData;
    private LocalDateTime createdAt;
}
