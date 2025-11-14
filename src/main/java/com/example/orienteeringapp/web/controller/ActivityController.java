package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.dto.ActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityResponseDto;
import com.example.orienteeringapp.application.service.ActivityService;
import com.example.orienteeringapp.infrastructure.security.annotation.IsActivityOwner;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@Tag(name = "Activities")
@PreAuthorize("isAuthenticated()")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<CreateActivityResponseDto> createActivity(@Valid @RequestBody CreateActivityDto dto) {
        CreateActivityResponseDto response = activityService.createActivity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDto> getActivity(@PathVariable Long id) {
        return activityService.getActivity(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<ActivityDto> getActivitiesByUser(@PathVariable Long userId) {
        return activityService.getActivitiesByUserId(userId);
    }

    @GetMapping("/map/{mapId}")
    public List<ActivityDto> getActivitiesByMap(@PathVariable Long mapId) {
        return activityService.getActivitiesByMapId(mapId);
    }

    @DeleteMapping("/{id}")
    @IsActivityOwner
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }
}
