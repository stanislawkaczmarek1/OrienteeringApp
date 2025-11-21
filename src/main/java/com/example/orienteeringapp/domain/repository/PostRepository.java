package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.Post;

public interface PostRepository {
    Post save(Post post);
    void deleteById(Long id);
}
