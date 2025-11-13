package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMapDto {
    private Long userId;
    private String name;
    private String description;
    private String location;
    private java.util.Map<String, Object> mapData = new HashMap<>();
}
