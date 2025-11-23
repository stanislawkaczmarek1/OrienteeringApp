package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateUserFollowsDto;
import com.example.orienteeringapp.application.dto.CreateUserFollowsResponseDto;
import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFollowsService {
    private final UserFollowsRepository repository;

    public UserFollowsService(UserFollowsRepository repository) {
        this.repository = repository;
    }

    public CreateUserFollowsResponseDto createUserFollows(CreateUserFollowsDto dto) {
        UserFollows userFollows = new UserFollows(
                dto.getFollowerId(),
                dto.getFollowingId(),
                null
        );

        UserFollows created = repository.save(userFollows);

        return new CreateUserFollowsResponseDto(
                created.getFollowerId(),
                created.getFollowingId()
        );
    }


    public void deleteUserFollows(Long followerId, Long followingId) {
        repository.deleteByFollowerIdAndFollowingId(followerId,followingId);
    }
}
