package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private Long id;
    private Long userId;
    private Long mapId;
    private String title;
    private LocalDateTime startTime;
    private Duration duration;
    private BigDecimal distance;
    private List<PathPointDto> pathData;
    private LocalDateTime createdAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PathPointDto {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;
    }
}
