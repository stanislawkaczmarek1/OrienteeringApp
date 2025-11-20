package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
}
