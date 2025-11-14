package com.example.orienteeringapp.infrastructure.entity;

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
import java.util.Objects;

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
    @JoinColumn(name = "map_id")
    private MapEntity map;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "duration_ms")
    private Long durationMs;

    @Column(name = "distance", precision = 6, scale = 2)
    private BigDecimal distance;

    @Type(JsonBinaryType.class)
    @Column(name = "path_data", columnDefinition = "TEXT", nullable = false)
    private List<PathPointData> pathData = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Duration getDuration() {
        return durationMs != null ? Duration.ofMillis(durationMs) : null;
    }

    public void setDuration(Duration duration) {
        this.durationMs = duration != null ? duration.toMillis() : null;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PathPointData {
        private Double latitude;
        private Double longitude;
        private LocalDateTime timestamp;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PathPointData that = (PathPointData) o;
            return Objects.equals(latitude, that.latitude) &&
                   Objects.equals(longitude, that.longitude) &&
                   Objects.equals(timestamp, that.timestamp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(latitude, longitude, timestamp);
        }
    }
}
