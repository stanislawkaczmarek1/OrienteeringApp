package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.CreateFollowRequestResponseDto;
import com.example.orienteeringapp.application.service.FollowRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseFollowRequestController {

    protected final FollowRequestService followRequestService;

    protected BaseFollowRequestController(FollowRequestService followRequestService) {
        this.followRequestService = followRequestService;
    }

    @PostMapping
    public ResponseEntity<CreateFollowRequestResponseDto> createFollowRequest(@RequestBody CreateFollowRequestDto dto) {
        CreateFollowRequestResponseDto responseDto = followRequestService.createFollowRequest(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowRequest(@PathVariable Long id) {
        followRequestService.deleteFollowRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


