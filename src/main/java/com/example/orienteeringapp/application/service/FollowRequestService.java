package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.application.dto.PendingFollowReqResponseDto;
import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;

import com.example.orienteeringapp.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowRequestService {
    private final FollowRequestRepository followRequestRepository;
    private final UserFollowsRepository userFollowsRepository;
    private final UserRepository userRepository;

    public FollowRequestService(FollowRequestRepository followRequestRepository, UserFollowsRepository userFollowsRepository, UserRepository userRepository) {
        this.followRequestRepository = followRequestRepository;
        this.userFollowsRepository = userFollowsRepository;
        this.userRepository = userRepository;
    }

    public FollowRequestResponseDto createFollowRequest(CreateFollowRequestDto dto, String requesterId) {
        Long id = Long.parseLong(requesterId);
        FollowRequest request = new FollowRequest(
                null,
                id,
                dto.getTargetId(),
                null
        );

        FollowRequest created = followRequestRepository.save(request);

        return new FollowRequestResponseDto(created.getId(),
                created.getRequesterId(),
                created.getTargetId(),
                created.getCreatedAt());
    }


    public List<PendingFollowReqResponseDto> getPendingForTarget(String targetId) {
        Long id = Long.parseLong(targetId);
        List<FollowRequest> pendingForTarget = followRequestRepository.findPendingForTarget(id);

        List<PendingFollowReqResponseDto> responseDtos = new ArrayList<>();
        for (FollowRequest followRequest : pendingForTarget) {
            Optional<User> user = userRepository.findById(followRequest.getRequesterId());
            if (user.isEmpty()) {
               throw new IllegalArgumentException("Requester user not found");
            }
            responseDtos.add(new PendingFollowReqResponseDto(
                    followRequest.getId(),
                    followRequest.getRequesterId(),
                    followRequest.getTargetId(),
                    followRequest.getCreatedAt(),
                    user.get().getFullName(),
                    user.get().getUsername()
            ));
        }

        return responseDtos;
    }

    public Boolean existsByRequesterAndTarget(String requesterId, Long targetId) {
        Long id = Long.parseLong(requesterId);
        Optional<FollowRequest> optionalFollowRequest = followRequestRepository.findByRequesterAndTarget(id, targetId);
        return optionalFollowRequest.isPresent();

    }

    @Transactional
    public void acceptRequest(Long id) {
        Optional<FollowRequest> optionalFollowRequest = followRequestRepository.findById(id);
        if (optionalFollowRequest.isPresent()) {
            FollowRequest followRequest = optionalFollowRequest.get();
            userFollowsRepository.save(new UserFollows(
                    followRequest.getRequesterId(),
                    followRequest.getTargetId(),
                    null
            ));
            followRequestRepository.delete(id);
        } else {
            throw new IllegalArgumentException("Follow request with this id does not exists");
        }

    }

    public void rejectRequest(Long id) {
        followRequestRepository.delete(id);
    }

    public void withdraw(String requesterId, Long targetUserId) {
        Long id = Long.parseLong(requesterId);
        followRequestRepository.deleteByRequesterAndTarget(id, targetUserId);
    }
}
