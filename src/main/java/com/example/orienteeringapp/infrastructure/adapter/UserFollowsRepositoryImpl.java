package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.entity.UserFollowsEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaUserFollowsRepository;
import org.springframework.stereotype.Component;

@Component
public class UserFollowsRepositoryImpl implements UserFollowsRepository {
    private final JpaUserFollowsRepository jpaUserFollowsRepository;

    public UserFollowsRepositoryImpl(JpaUserFollowsRepository jpaUserFollowsRepository) {
        this.jpaUserFollowsRepository = jpaUserFollowsRepository;
    }

    @Override
    public UserFollows save(UserFollows userFollows) {
        UserFollowsEntity entity = new UserFollowsEntity();

        UserEntity follower = new UserEntity();
        follower.setId(userFollows.getFollowerId());
        entity.setFollower(follower);

        UserEntity following = new UserEntity();
        following.setId(userFollows.getFollowingId());
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
