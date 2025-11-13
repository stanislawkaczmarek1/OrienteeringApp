package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Map {
  private Long id;
  private Long userId;
  private String name;
  private String description;
  private String location;
  private java.util.Map<String, Object> mapData;
  private LocalDateTime createdAt;
}
