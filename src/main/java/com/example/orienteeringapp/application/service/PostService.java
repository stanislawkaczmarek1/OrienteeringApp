package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.CreatePostResponseDto;
import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public CreatePostResponseDto createPost(CreatePostDto dto) {
        Post post = new Post(
                null,
                dto.getUserId(),
                dto.getContent(),
                dto.getMapId(),
                dto.getActivityId(),
                dto.getVisibility(),
                null
        );

        Post created = repository.save(post);

        return new CreatePostResponseDto(created.getId());
    }


    public void deletePost(Long id) {
        repository.deleteById(id);
    }
}
