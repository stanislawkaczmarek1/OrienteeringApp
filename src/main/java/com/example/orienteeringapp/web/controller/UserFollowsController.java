package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.dto.CreateUserFollowsDto;
import com.example.orienteeringapp.application.dto.CreateUserFollowsResponseDto;
import com.example.orienteeringapp.application.service.UserFollowsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-follows")
@Tag(name = "UserFollows")
public class UserFollowsController {
    private final UserFollowsService userFollowsService;

    public UserFollowsController(UserFollowsService userFollowsService) {
        this.userFollowsService = userFollowsService;
    }

    @PostMapping
    public ResponseEntity<CreateUserFollowsResponseDto> createUser(@RequestBody CreateUserFollowsDto dto) {
        CreateUserFollowsResponseDto responseDto = userFollowsService.createUserFollows(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{followerId}/{followingId}")
    public ResponseEntity<Void> deleteUserFollows(
            @PathVariable Long followerId,
            @PathVariable Long followingId) {
        userFollowsService.deleteUserFollows(followerId, followingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}