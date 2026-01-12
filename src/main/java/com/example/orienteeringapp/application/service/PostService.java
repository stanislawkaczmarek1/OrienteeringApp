package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.PostResponseDto;
import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public PostResponseDto createPost(CreatePostDto dto, String userId) {
        Long id = Long.parseLong(userId);
        Post post = new Post(
                null,
                id,
                dto.getContent(),
                dto.getMapId(),
                dto.getActivityId(),
                dto.getVisibility(),
                null
        );

        Post created = repository.save(post);

        return new PostResponseDto(
                created.getId(),
                created.getUserId(),
                created.getContent(),
                created.getMapId(),
                created.getActivityId(),
                created.getVisibility(),
                created.getCreatedAt()
        );
    }


    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    public PostResponseDto getById(Long id) {
        return null;
    }

    public List<PostResponseDto> getByUserId(Long id) {
        return new ArrayList<>();
    }
    public List<PostResponseDto> getByUserId(String id) {
        return new ArrayList<>();
    }
    public List<PostResponseDto> getFeedForUser(String userId) {
        return new ArrayList<>();
    }
}
