package com.example.orienteeringapp.infrastructure.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "maps")
@Getter
@Setter
@NoArgsConstructor
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "location", length = 255)
    private String location;

    @Type(JsonBinaryType.class)
    @Column(name = "map_data", columnDefinition = "TEXT", nullable = false)
    private MapData mapData = new MapData();

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
    public static class MapData {
        private List<ControlPoint> controlPoints = new ArrayList<>();

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapData mapData = (MapData) o;
            return Objects.equals(controlPoints, mapData.controlPoints);
        }

        @Override
        public int hashCode() {
            return Objects.hash(controlPoints);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ControlPoint {
        private Double latitude;
        private Double longitude;
        private Integer id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ControlPoint that = (ControlPoint) o;
            return Objects.equals(latitude, that.latitude) &&
                   Objects.equals(longitude, that.longitude) &&
                   Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(latitude, longitude, id);
        }
    }
}
