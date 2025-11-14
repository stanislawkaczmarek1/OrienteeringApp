package com.example.orienteeringapp.infrastructure.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id", nullable = false)
    private MapEntity map;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "duration", columnDefinition = "interval")
    private Duration duration;

    @Column(name = "distance", precision = 6, scale = 2)
    private BigDecimal distance;

    @Type(JsonBinaryType.class)
    @Column(name = "path_data", columnDefinition = "jsonb", nullable = false)
    private List<PathPointData> pathData = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PathPointData {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;
    }
}
