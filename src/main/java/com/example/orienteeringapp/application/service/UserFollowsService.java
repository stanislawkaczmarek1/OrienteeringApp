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

    public UserFollowsResponseDto createUserFollows(CreateUserFollowsDto dto) {
        UserFollows userFollows = new UserFollows(
                dto.getFollowerId(),
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


    public void deleteUserFollows(Long followerId, Long followingId) {
        repository.deleteByFollowerIdAndFollowingId(followerId,followingId);
    }

    public boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        return repository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}
