package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreateUserFollowsDto;
import com.example.orienteeringapp.application.dto.UserFollowsResponseDto;
import com.example.orienteeringapp.application.service.UserFollowsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseUserFollowsController {

    protected final UserFollowsService userFollowsService;

    protected BaseUserFollowsController(UserFollowsService userFollowsService) {
        this.userFollowsService = userFollowsService;
    }

    @PostMapping
    public ResponseEntity<UserFollowsResponseDto> createUserFollows(@RequestBody CreateUserFollowsDto dto) {
        UserFollowsResponseDto responseDto = userFollowsService.createUserFollows(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{followerId}/{followingId}")
    public ResponseEntity<Void> deleteUserFollows(
            @PathVariable Long followerId,
            @PathVariable Long followingId) {
        userFollowsService.deleteUserFollows(followerId, followingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> exists(
            @RequestParam Long followerId,
            @RequestParam Long followingId) {
        boolean exists = userFollowsService.existsByFollowerIdAndFollowingId(followerId, followingId);
        return ResponseEntity.ok(exists);
    }

}



