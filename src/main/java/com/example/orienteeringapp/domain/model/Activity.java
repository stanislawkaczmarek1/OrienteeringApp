package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class Activity {
    private Long id;
    private Long userId;
    private Long mapId;
    private String title;
    private LocalDateTime startTime;
    private Duration duration;
    private BigDecimal distance;
    private List<PathPoint> pathData;
    private LocalDateTime createdAt;

    @Getter
    @AllArgsConstructor
    public static class PathPoint {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;
    }
}
