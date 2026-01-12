package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.PostResponseDto;
import com.example.orienteeringapp.application.service.PostService;
import com.example.orienteeringapp.infrastructure.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BasePostController {

    protected final PostService postService;

    protected BasePostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody CreatePostDto dto,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        PostResponseDto responseDto = postService.createPost(dto, principal.getUserId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //todo
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable Long id) {
        PostResponseDto responseDto = postService.getById(id);
        return ResponseEntity.ok(responseDto);
    }
    //todo
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PostResponseDto>> getByUserId(@PathVariable Long userId) {
        List<PostResponseDto> responseDto = postService.getByUserId(userId);
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @GetMapping("/me")
    public ResponseEntity<List<PostResponseDto>> getMyPost(@AuthenticationPrincipal UserPrincipal principal) {
        List<PostResponseDto> responseDto = postService.getByUserId(principal.getUserId());
        return ResponseEntity.ok(responseDto);
    }

    //todo
    @GetMapping("/feed")
    public ResponseEntity<List<PostResponseDto>> getFeed(@AuthenticationPrincipal UserPrincipal principal) {
        List<PostResponseDto> responseDto = postService.getFeedForUser(principal.getUserId());
        return ResponseEntity.ok(responseDto);
    }

}



