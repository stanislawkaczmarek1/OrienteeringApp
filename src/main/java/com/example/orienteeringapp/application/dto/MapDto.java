package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapDto {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String location;
    private MapData mapData;
    private LocalDateTime createdAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MapData {
        private List<ControlPoint> controlPoints;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ControlPoint {
        private Double latitude;
        private Double longitude;
        private Integer id;
    }
}
