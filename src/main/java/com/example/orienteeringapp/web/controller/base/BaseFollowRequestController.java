package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.application.service.FollowRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseFollowRequestController {

    protected final FollowRequestService followRequestService;

    protected BaseFollowRequestController(FollowRequestService followRequestService) {
        this.followRequestService = followRequestService;
    }

    //todo
    @GetMapping("/{id}")
    public ResponseEntity<FollowRequestResponseDto> getById(@PathVariable Long id) {
        FollowRequestResponseDto responseDto = followRequestService.getById(id);
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @GetMapping("/targets/{id}/pending")
    public ResponseEntity<List<FollowRequestResponseDto>> getPendingForTarget(@PathVariable Long id) {
        List<FollowRequestResponseDto> responseDto = followRequestService.getPendingForTarget(id);
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @GetMapping("/requester/{requesterId}/target/{targetId}")
    public ResponseEntity<FollowRequestResponseDto> getByRequesterAndTarget(@PathVariable Long requesterId,
                                                                            @PathVariable Long targetId) {
        FollowRequestResponseDto responseDto = followRequestService.getByRequesterAndTarget(requesterId, targetId);
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long id) {
        followRequestService.acceptRequest(id);
        return ResponseEntity.noContent().build();
    }

    //todo
    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long id) {
        followRequestService.rejectRequest(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<FollowRequestResponseDto> createFollowRequest(@RequestBody CreateFollowRequestDto dto) {
        FollowRequestResponseDto responseDto = followRequestService.createFollowRequest(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowRequest(@PathVariable Long id) {
        followRequestService.deleteFollowRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


