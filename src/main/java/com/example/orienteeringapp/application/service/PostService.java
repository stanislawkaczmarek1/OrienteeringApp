package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreatePostDto;
import com.example.orienteeringapp.application.dto.PostResponseDto;
import com.example.orienteeringapp.domain.model.Activity;
import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.model.enums.PostVisibility;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import com.example.orienteeringapp.domain.repository.PostRepository;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;
import com.example.orienteeringapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final UserFollowsRepository userFollowsRepository;

    public PostService(PostRepository postRepository, ActivityRepository activityRepository, UserRepository userRepository, UserFollowsRepository userFollowsRepository) {
        this.postRepository = postRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
        this.userFollowsRepository = userFollowsRepository;
    }

    public PostResponseDto createPost(CreatePostDto dto, String userId) {
        Long id = Long.parseLong(userId);
        Post post = new Post(
                null,
                id,
                dto.getContent(),
                dto.getMapId(),
                dto.getActivityId(),
                dto.getVisibility(),
                null
        );
        Post createdPost = postRepository.save(post);

        Optional<User> optionalUser = userRepository.findById(createdPost.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Optional<Activity> optionalActivity = activityRepository.findById(createdPost.getActivityId());

        return mapModelsToDto(createdPost, optionalUser.get(), optionalActivity.orElse(null));
    }


    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    public PostResponseDto getById(Long id, String currentUserIdStr) {
        Long currentUserId = Long.parseLong(currentUserIdStr);

        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) {
            throw new IllegalArgumentException("Post with this id does not exists");
        }
        Post post = optionalPost.get();

        boolean isVisible = isPostVisibleForUser(post, currentUserId);
        if (!isVisible) {
            throw new IllegalArgumentException("Post with this id is not visible for current user");
        }

        Optional<User> optionalUser = userRepository.findById(post.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Optional<Activity> optionalActivity = activityRepository.findById(post.getActivityId());

        return mapModelsToDto(post, optionalUser.get(), optionalActivity.orElse(null));
    }

    public List<PostResponseDto> getByUserId(Long id, String currentUserIdStr) {
        Long currentUserId = Long.parseLong(currentUserIdStr);

        List<Post> posts = postRepository.findByUserId(id);

        List<Post> filteredPosts = posts.stream().filter(post ->
                isPostVisibleForUser(post, currentUserId)
                ).toList();

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : filteredPosts) {
            Optional<Activity> optionalActivity = activityRepository.findById(post.getActivityId());
            responseDtos.add(mapModelsToDto(post, optionalUser.get(), optionalActivity.orElse(null)));
        }

        return responseDtos;
    }

    public List<PostResponseDto> getMyPosts(String id) {
        Long userId = Long.parseLong(id);
        List<Post> posts = postRepository.findByUserId(userId);

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : posts) {
            Optional<Activity> optionalActivity = activityRepository.findById(post.getActivityId());
            responseDtos.add(mapModelsToDto(post, optionalUser.get(), optionalActivity.orElse(null)));
        }

        return responseDtos;
    }

    public List<PostResponseDto> getFeedForUser(String id) {
        Long userId = Long.parseLong(id);
        List<Post> posts = postRepository.findFeedForUser(userId);

        List<Post> filteredPosts = posts.stream().filter(post ->
            isPostVisibleForUser(post, userId)
        ).toList();

        List<PostResponseDto> responseDtos = new ArrayList<>();
        for (Post post : filteredPosts) {
            Optional<User> optionalUser = userRepository.findById(post.getUserId());
            if (optionalUser.isEmpty()) {
                throw new IllegalArgumentException("User not found");
            }
            Optional<Activity> optionalActivity = activityRepository.findById(post.getActivityId());
            responseDtos.add(mapModelsToDto(post, optionalUser.get(), optionalActivity.orElse(null)));
        }

        return responseDtos;
    }

    private boolean isPostVisibleForUser(Post post, Long currentUserId) {
        Long ownerUserId = post.getUserId();
        PostVisibility visibility = post.getVisibility();

        if (currentUserId == null || ownerUserId == null) {
            return false;
        }

        return switch (visibility) {
            case FOLLOWERS ->
                    Objects.equals(ownerUserId, currentUserId) || userFollowsRepository.existsByFollowerIdAndFollowingId(currentUserId, ownerUserId);
            case PRIVATE -> Objects.equals(ownerUserId, currentUserId);
            case PUBLIC -> true;
        };
    }

    private PostResponseDto mapModelsToDto(Post post, User user,@Nullable Activity activity) {
        String title = "";
        String distance = "";
        String duration = "";
        if (activity != null) {
            title = activity.getTitle();
            distance = activity.getDistance().toEngineeringString();
            duration = activity.getDuration().toString();
        }

        return new PostResponseDto(
                post.getId(),
                post.getUserId(),
                post.getContent(),
                post.getMapId(),
                post.getActivityId(),
                post.getVisibility(),
                post.getCreatedAt(),
                title,
                user.getFullName(),
                user.getUsername(),
                distance,
                duration
        );
    }
}
