package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateMapDto;
import com.example.orienteeringapp.application.dto.CreateMapResponseDto;
import com.example.orienteeringapp.application.dto.MapDto;
import com.example.orienteeringapp.application.service.MapService;
import com.example.orienteeringapp.infrastructure.security.annotation.IsMapOwner;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("isAuthenticated()")
public abstract class BaseMapController {

    protected final MapService mapService;

    protected BaseMapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public CreateMapResponseDto createMap(@RequestBody CreateMapDto dto) {
        return mapService.createMap(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MapDto> getMap(@PathVariable Long id) {
        return mapService.getMap(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<MapDto> getMapsByUserId(@PathVariable Long userId) {
        return mapService.getMapsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @IsMapOwner
    public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
        mapService.deleteMap(id);
        return ResponseEntity.noContent().build();
    }
}



