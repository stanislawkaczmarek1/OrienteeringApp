package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String location;
    private Map<String, Object> mapData;
    private LocalDateTime createdAt;
}
