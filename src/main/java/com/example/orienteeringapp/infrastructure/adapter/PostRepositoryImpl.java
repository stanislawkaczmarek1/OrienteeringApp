package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.repository.PostRepository;
import com.example.orienteeringapp.infrastructure.entity.ActivityEntity;
import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import com.example.orienteeringapp.infrastructure.entity.PostEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaPostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;

    public PostRepositoryImpl(JpaPostRepository jpaPostRepository) {
        this.jpaPostRepository = jpaPostRepository;
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity();

        entity.setId(post.getId());
        UserEntity userEntity = new UserEntity();
        userEntity.setId(post.getUserId());
        entity.setUser(userEntity);

        entity.setContent(post.getContent());

        MapEntity mapEntity = new MapEntity();
        mapEntity.setId(post.getMapId());
        entity.setMap(mapEntity);

        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setId(post.getActivityId());
        entity.setActivity(activityEntity);

        entity.setVisibility(post.getVisibility());
        entity.setCreatedAt(post.getCreatedAt());


        PostEntity saved = jpaPostRepository.save(entity);
        return postEntityToDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaPostRepository.deleteById(id);
    }

    private Post postEntityToDomain(PostEntity entity) {
        return new Post(
                entity.getId(),
                entity.getUser().getId(),
                entity.getContent(),
                entity.getMap().getId(),
                entity.getActivity().getId(),
                entity.getVisibility(),
                entity.getCreatedAt()
        );
    }
}
