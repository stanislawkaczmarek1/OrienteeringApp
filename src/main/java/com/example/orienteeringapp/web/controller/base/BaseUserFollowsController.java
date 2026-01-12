package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateUserFollowsDto;
import com.example.orienteeringapp.application.dto.UserFollowsResponseDto;
import com.example.orienteeringapp.application.service.UserFollowsService;
import com.example.orienteeringapp.infrastructure.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

public abstract class BaseUserFollowsController {

    protected final UserFollowsService userFollowsService;

    protected BaseUserFollowsController(UserFollowsService userFollowsService) {
        this.userFollowsService = userFollowsService;
    }

    @PostMapping
    public ResponseEntity<UserFollowsResponseDto> createUserFollows(
            @RequestBody CreateUserFollowsDto dto,
            @AuthenticationPrincipal UserPrincipal principal) {
        UserFollowsResponseDto responseDto = userFollowsService.createUserFollows(dto, principal.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{followingId}")
    public ResponseEntity<Void> deleteUserFollows(
            @PathVariable Long followingId,
            @AuthenticationPrincipal UserPrincipal principal) {
        userFollowsService.deleteUserFollows(principal.getUserId(), followingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{followingId}/exists")
    public ResponseEntity<Boolean> exists(
            @PathVariable Long followingId,
            @AuthenticationPrincipal UserPrincipal principal
            ) {
        boolean exists = userFollowsService.existsByFollowerIdAndFollowingId(principal.getUserId(), followingId);
        return ResponseEntity.ok(exists);
    }

}



