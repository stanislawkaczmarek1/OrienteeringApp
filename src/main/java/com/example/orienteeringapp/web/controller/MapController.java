package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.service.MapService;
import com.example.orienteeringapp.application.dto.CreateMapDto;
import com.example.orienteeringapp.application.dto.CreateMapResponseDto;
import com.example.orienteeringapp.application.dto.MapDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
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
    public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
        mapService.deleteMap(id);
        return ResponseEntity.noContent().build();
    }
}
