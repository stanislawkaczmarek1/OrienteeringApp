package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.PostResponseDto;
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
    public ResponseEntity<PostResponseDto> createPost(@RequestBody CreatePostDto dto) {
        PostResponseDto responseDto = postService.createPost(dto);
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
    public ResponseEntity<PostResponseDto> getByUserId(@PathVariable Long id) {
        PostResponseDto responseDto = postService.getByUserId(id);
        return ResponseEntity.ok(responseDto);
    }
    //todo
    @GetMapping("/users/{userId}/public")
    public ResponseEntity<PostResponseDto> getPublicByUserId(@PathVariable Long id) {
        PostResponseDto responseDto = postService.getPublicByUserId(id);
        return ResponseEntity.ok(responseDto);
    }
}



