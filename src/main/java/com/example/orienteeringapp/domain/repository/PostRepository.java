package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    void deleteById(Long id);
    Optional<Post> findById(Long id);

    List<Post> findByUserId(Long userId);
    List<Post> findFeedForUser(Long userId);
}
