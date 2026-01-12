package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.repository.PostRepository;
import com.example.orienteeringapp.infrastructure.entity.ActivityEntity;
import com.example.orienteeringapp.infrastructure.entity.MapEntity;
import com.example.orienteeringapp.infrastructure.entity.PostEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaActivityRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaMapRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaPostRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostRepositoryImpl implements PostRepository {
    private final JpaPostRepository jpaPostRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaMapRepository jpaMapRepository;
    private final JpaActivityRepository jpaActivityRepository;

    public PostRepositoryImpl(JpaPostRepository jpaPostRepository, JpaUserRepository jpaUserRepository, JpaMapRepository jpaMapRepository, JpaActivityRepository jpaActivityRepository) {
        this.jpaPostRepository = jpaPostRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaMapRepository = jpaMapRepository;
        this.jpaActivityRepository = jpaActivityRepository;
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity();

        UserEntity user = jpaUserRepository.findById(post.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUser(user);

        entity.setContent(post.getContent());

        MapEntity map = jpaMapRepository.findById(post.getMapId())
                .orElseThrow(() -> new RuntimeException("Map not found"));
        entity.setMap(map);

        ActivityEntity activity = jpaActivityRepository.findById(post.getActivityId())
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        entity.setActivity(activity);

        entity.setVisibility(post.getVisibility());

        PostEntity saved = jpaPostRepository.save(entity);
        return postEntityToDomain(saved);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaPostRepository.findById(id)
                .map(this::postEntityToDomain);
    }

    @Override
    public List<Post> findFeedForUser(Long userId) {
        List<PostEntity> entities = jpaPostRepository.findFeedForUser(userId);
        return entities.stream()
                .map(this::postEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        List<PostEntity> entities = jpaPostRepository.findByUser_Id(userId);
        return entities.stream()
                .map(this::postEntityToDomain)
                .collect(Collectors.toList());
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
