package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.ActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityResponseDto;
import com.example.orienteeringapp.domain.model.Activity;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public CreateActivityResponseDto createActivity(CreateActivityDto dto) {
        Activity activity = new Activity(
                null,
                dto.getUserId(),
                dto.getMapId(),
                dto.getTitle(),
                dto.getStartTime(),
                dto.getDuration(),
                dto.getDistance(),
                mapPathPoints(dto.getPathData()),
                null
        );

        Activity created = repository.save(activity);
        return new CreateActivityResponseDto(created.getId());
    }

    public Optional<ActivityDto> getActivity(Long id) {
        return repository.findById(id)
                .map(this::activityToDto);
    }

    public List<ActivityDto> getActivitiesByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(this::activityToDto)
                .collect(Collectors.toList());
    }

    public List<ActivityDto> getActivitiesByMapId(Long mapId) {
        return repository.findByMapId(mapId).stream()
                .map(this::activityToDto)
                .collect(Collectors.toList());
    }

    public void deleteActivity(Long id) {
        repository.delete(id);
    }

    private ActivityDto activityToDto(Activity activity) {
        return new ActivityDto(
                activity.getId(),
                activity.getUserId(),
                activity.getMapId(),
                activity.getTitle(),
                activity.getStartTime(),
                activity.getDuration(),
                activity.getDistance(),
                mapPathPointsToDto(activity.getPathData()),
                activity.getCreatedAt()
        );
    }

    private List<Activity.PathPoint> mapPathPoints(List<CreateActivityDto.PathPointDto> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream()
                .map(dto -> new Activity.PathPoint(
                        dto.getLatitude(),
                        dto.getLongitude(),
                        dto.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    private List<ActivityDto.PathPointDto> mapPathPointsToDto(List<Activity.PathPoint> pathPoints) {
        if (pathPoints == null) {
            return new ArrayList<>();
        }
        return pathPoints.stream()
                .map(point -> new ActivityDto.PathPointDto(
                        point.getLatitude(),
                        point.getLongitude(),
                        point.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}
