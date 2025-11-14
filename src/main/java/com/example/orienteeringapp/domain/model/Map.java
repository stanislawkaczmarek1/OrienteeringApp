package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Map {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String location;
    private MapData mapData;
    private LocalDateTime createdAt;

    @Getter
    @AllArgsConstructor
    public static class MapData {
        private List<ControlPoint> controlPoints;
    }

    @Getter
    @AllArgsConstructor
    public static class ControlPoint {
        private Double latitude;
        private Double longitude;
        private Integer id;
    }
}
