package com.example.orienteeringapp.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateActivityDto {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Map ID is required")
    private Long mapId;

    private String title;

    private LocalDateTime startTime;

    private Duration duration;

    @Positive(message = "Distance must be positive")
    private BigDecimal distance;

    @NotNull(message = "Path data is required")
    private List<PathPointDto> pathData = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PathPointDto {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;
    }
}
