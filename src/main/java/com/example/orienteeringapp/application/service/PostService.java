package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.PostResponseDto;
import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Post> optionalPost = repository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            return new PostResponseDto(
                    post.getId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getMapId(),
                    post.getActivityId(),
                    post.getVisibility(),
                    post.getCreatedAt()
            );
        } else {
            throw new IllegalArgumentException("Post with this id does not exists");
        }
    }

    public List<PostResponseDto> getByUserId(Long id) {
        List<Post> posts = repository.findByUserId(id);

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : posts) {
            responseDtos.add(new PostResponseDto(
                    post.getId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getMapId(),
                    post.getActivityId(),
                    post.getVisibility(),
                    post.getCreatedAt()
            ));
        }

        return responseDtos;
    }
    public List<PostResponseDto> getByUserId(String id) {
        Long userId = Long.parseLong(id);
        List<Post> posts = repository.findByUserId(userId);

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : posts) {
            responseDtos.add(new PostResponseDto(
                    post.getId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getMapId(),
                    post.getActivityId(),
                    post.getVisibility(),
                    post.getCreatedAt()
            ));
        }

        return responseDtos;
    }
    public List<PostResponseDto> getFeedForUser(String id) {
        Long userId = Long.parseLong(id);
        List<Post> posts = repository.findFeedForUser(userId);

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : posts) {
            responseDtos.add(new PostResponseDto(
                    post.getId(),
                    post.getUserId(),
                    post.getContent(),
                    post.getMapId(),
                    post.getActivityId(),
                    post.getVisibility(),
                    post.getCreatedAt()
            ));
        }

        return responseDtos;
    }
}
