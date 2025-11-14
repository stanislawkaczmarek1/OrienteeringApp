package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.Activity;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import com.example.orienteeringapp.infrastructure.entity.ActivityEntity;
import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaActivityRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaMapRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ActivityRepositoryImpl implements ActivityRepository {

    private final JpaActivityRepository jpaActivityRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaMapRepository jpaMapRepository;

    public ActivityRepositoryImpl(
            JpaActivityRepository jpaActivityRepository,
            JpaUserRepository jpaUserRepository,
            JpaMapRepository jpaMapRepository
    ) {
        this.jpaActivityRepository = jpaActivityRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaMapRepository = jpaMapRepository;
    }

    @Override
    public Activity save(Activity activity) {
        UserEntity user = jpaUserRepository.findById(activity.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MapEntity map = jpaMapRepository.findById(activity.getMapId())
                .orElseThrow(() -> new RuntimeException("Map not found"));

        ActivityEntity entity = new ActivityEntity();
        entity.setId(activity.getId());
        entity.setUser(user);
        entity.setMap(map);
        entity.setTitle(activity.getTitle());
        entity.setStartTime(activity.getStartTime());
        entity.setDuration(activity.getDuration());
        entity.setDistance(activity.getDistance());
        entity.setPathData(mapPathDataToEntity(activity.getPathData()));

        ActivityEntity saved = jpaActivityRepository.save(entity);
        return activityEntityToDomain(saved);
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return jpaActivityRepository.findById(id)
                .map(this::activityEntityToDomain);
    }

    @Override
    public List<Activity> findByUserId(Long userId) {
        return jpaActivityRepository.findByUserId(userId).stream()
                .map(this::activityEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Activity> findByMapId(Long mapId) {
        return jpaActivityRepository.findByMapId(mapId).stream()
                .map(this::activityEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        jpaActivityRepository.deleteById(id);
    }

    private Activity activityEntityToDomain(ActivityEntity entity) {
        return new Activity(
                entity.getId(),
                entity.getUser().getId(),
                entity.getMap() != null ? entity.getMap().getId() : null,
                entity.getTitle(),
                entity.getStartTime(),
                entity.getDuration(),
                entity.getDistance(),
                mapPathDataToDomain(entity.getPathData()),
                entity.getCreatedAt()
        );
    }

    private List<ActivityEntity.PathPointData> mapPathDataToEntity(List<Activity.PathPoint> pathData) {
        if (pathData == null) {
            return new ArrayList<>();
        }
        return pathData.stream()
                .map(point -> {
                    ActivityEntity.PathPointData data = new ActivityEntity.PathPointData();
                    data.setLatitude(point.getLatitude());
                    data.setLongitude(point.getLongitude());
                    data.setTimestamp(point.getTimestamp());
                    return data;
                })
                .collect(Collectors.toList());
    }

    private List<Activity.PathPoint> mapPathDataToDomain(List<ActivityEntity.PathPointData> pathData) {
        if (pathData == null) {
            return new ArrayList<>();
        }
        return pathData.stream()
                .map(data -> new Activity.PathPoint(
                        data.getLatitude(),
                        data.getLongitude(),
                        data.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
