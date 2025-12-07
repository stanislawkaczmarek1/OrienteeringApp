package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.ActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityDto;
import com.example.orienteeringapp.application.dto.CreateActivityResponseDto;
import com.example.orienteeringapp.application.service.ActivityService;
import com.example.orienteeringapp.infrastructure.security.annotation.IsActivityOwner;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("isAuthenticated()")
public abstract class BaseActivityController {

    protected final ActivityService activityService;

    protected BaseActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public ResponseEntity<CreateActivityResponseDto> createActivity(@Valid @RequestBody CreateActivityDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(activityService.createActivity(dto));
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
