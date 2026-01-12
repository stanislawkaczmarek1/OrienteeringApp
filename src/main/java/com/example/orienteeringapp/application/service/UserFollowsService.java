package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateUserFollowsDto;
import com.example.orienteeringapp.application.dto.UserFollowsResponseDto;
import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFollowsService {
    private final UserFollowsRepository repository;

    public UserFollowsService(UserFollowsRepository repository) {
        this.repository = repository;
    }

    public UserFollowsResponseDto createUserFollows(CreateUserFollowsDto dto, String followerId) {
        Long id = Long.parseLong(followerId);
        UserFollows userFollows = new UserFollows(
                id,
                dto.getFollowingId(),
                null
        );

        UserFollows created = repository.save(userFollows);

        return new UserFollowsResponseDto(
                created.getFollowerId(),
                created.getFollowingId(),
                created.getCreatedAt()
        );
    }


    public void deleteUserFollows(String followerId, Long followingId) {
        Long id = Long.parseLong(followerId);
        repository.deleteByFollowerIdAndFollowingId(id,followingId);
    }

    public boolean existsByFollowerIdAndFollowingId(String followerId, Long followingId) {
        Long id = Long.parseLong(followerId);
        return repository.existsByFollowerIdAndFollowingId(id, followingId);
    }
}
