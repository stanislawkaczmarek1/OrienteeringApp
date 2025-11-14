package com.example.orienteeringapp.infrastructure.security;

import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import com.example.orienteeringapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service("ownershipChecker")
public class OwnershipChecker {

    private final MapRepository mapRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public OwnershipChecker(
            MapRepository mapRepository,
            ActivityRepository activityRepository,
            UserRepository userRepository
    ) {
        this.mapRepository = mapRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public boolean isMapOwner(Long mapId, String username) {
        return mapRepository.findById(mapId)
                .flatMap(map -> userRepository.findById(map.getUserId()))
                .map(user -> user.getUsername().equals(username))
                .orElse(false);
    }

    public boolean isActivityOwner(Long activityId, String username) {
        return activityRepository.findById(activityId)
                .flatMap(activity -> userRepository.findById(activity.getUserId()))
                .map(user -> user.getUsername().equals(username))
                .orElse(false);
    }

    public boolean isCurrentUser(Long userId, String username) {
        return userRepository.findById(userId)
                .map(user -> user.getUsername().equals(username))
                .orElse(false);
    }
}
