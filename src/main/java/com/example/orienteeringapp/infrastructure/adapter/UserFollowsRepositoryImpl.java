package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.entity.UserFollowsEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaUserFollowsRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFollowsRepositoryImpl implements UserFollowsRepository {
    private final JpaUserFollowsRepository jpaUserFollowsRepository;
    private final JpaUserRepository jpaUserRepository;

    public UserFollowsRepositoryImpl(JpaUserFollowsRepository jpaUserFollowsRepository, JpaUserRepository jpaUserRepository) {
        this.jpaUserFollowsRepository = jpaUserFollowsRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public UserFollows save(UserFollows userFollows) {
        UserFollowsEntity entity = new UserFollowsEntity();

        UserEntity follower = jpaUserRepository.findById(userFollows.getFollowerId())
                .orElseThrow(() -> new RuntimeException("Follower not found"));
        entity.setFollower(follower);

        UserEntity following = jpaUserRepository.findById(userFollows.getFollowingId())
                .orElseThrow(() -> new RuntimeException("Following user not found"));
        entity.setFollowing(following);


        UserFollowsEntity saved = jpaUserFollowsRepository.save(entity);
        return userFollowsEntityToDomain(saved);
    }

    @Override
    public void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        jpaUserFollowsRepository.deleteByFollower_IdAndFollowing_Id(followerId, followingId);
    }

    @Override
    public boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        return jpaUserFollowsRepository.existsByFollower_IdAndFollowing_Id(followerId, followingId);
    }

    private UserFollows userFollowsEntityToDomain(UserFollowsEntity entity) {
        return new UserFollows(
                entity.getFollower().getId(),
                entity.getFollowing().getId(),
                entity.getCreatedAt()
        );
    }

}
