package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.repository.PostRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaPostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    public PostRepositoryImpl(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository = jpaPostRepository;
    }
}
