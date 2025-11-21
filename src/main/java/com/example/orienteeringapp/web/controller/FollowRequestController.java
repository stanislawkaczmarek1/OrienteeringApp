package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.CreateFollowRequestResponseDto;
import com.example.orienteeringapp.application.service.FollowRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follow-requests")
@Tag(name = "FollowRequests")
public class FollowRequestController {
    private final FollowRequestService followRequestService;

    public FollowRequestController(FollowRequestService followRequestService) {
        this.followRequestService = followRequestService;
    }

    @PostMapping
    public ResponseEntity<CreateFollowRequestResponseDto> createUser(@RequestBody CreateFollowRequestDto dto) {
        CreateFollowRequestResponseDto responseDto = followRequestService.createFollowRequest(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowRequest(@PathVariable Long id) {
        followRequestService.deleteFollowRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}