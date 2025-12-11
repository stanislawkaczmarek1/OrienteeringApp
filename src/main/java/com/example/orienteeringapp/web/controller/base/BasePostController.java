package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.CreatePostResponseDto;
import com.example.orienteeringapp.application.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BasePostController {

    protected final PostService postService;

    protected BasePostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<CreatePostResponseDto> createPost(@RequestBody CreatePostDto dto) {
        CreatePostResponseDto responseDto = postService.createPost(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



