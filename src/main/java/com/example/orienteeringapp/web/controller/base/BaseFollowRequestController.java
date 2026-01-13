package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.application.dto.PendingFollowReqResponseDto;
import com.example.orienteeringapp.application.service.FollowRequestService;
import com.example.orienteeringapp.infrastructure.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseFollowRequestController {

    protected final FollowRequestService followRequestService;

    protected BaseFollowRequestController(FollowRequestService followRequestService) {
        this.followRequestService = followRequestService;
    }

    @PostMapping
    public ResponseEntity<FollowRequestResponseDto> createFollowRequest(
            @RequestBody CreateFollowRequestDto dto,
            @AuthenticationPrincipal UserPrincipal principal) {

        FollowRequestResponseDto responseDto = followRequestService.createFollowRequest(
                dto,
                principal.getUserId()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //todo
    @GetMapping("/pending")
    public ResponseEntity<List<PendingFollowReqResponseDto>> getPendingForTarget(
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        List<PendingFollowReqResponseDto> responseDto = followRequestService.getPendingForTarget(principal.getUserId());
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @PostMapping("/{id}/accept")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long id) {
        followRequestService.acceptRequest(id);
        return ResponseEntity.noContent().build();
    }

    //todo
    @DeleteMapping("/{id}/reject")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long id) {
        followRequestService.rejectRequest(id);
        return ResponseEntity.noContent().build();
    }

    //todo
    @GetMapping("/to/{targetId}/exists")
    public ResponseEntity<Boolean> existsByRequesterAndTarget(
            @PathVariable Long targetId,
            @AuthenticationPrincipal UserPrincipal principal) {
        Boolean responseDto = followRequestService.existsByRequesterAndTarget(principal.getUserId(), targetId);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/to/{targetUserId}")
    public ResponseEntity<Void> withdraw(
            @PathVariable Long targetUserId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        followRequestService.withdraw(
                principal.getUserId(),
                targetUserId
        );
        return ResponseEntity.noContent().build();
    }



}



