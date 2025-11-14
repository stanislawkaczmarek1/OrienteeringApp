package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMapDto {
    private Long userId;
    private String name;
    private String description;
    private String location;
    private MapData mapData = new MapData();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MapData {
        private List<ControlPoint> controlPoints = new ArrayList<>();
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
