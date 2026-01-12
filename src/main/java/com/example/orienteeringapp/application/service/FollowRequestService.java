package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.model.UserFollows;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import com.example.orienteeringapp.domain.repository.UserFollowsRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowRequestService {
    private final FollowRequestRepository followRequestRepository;
    private final UserFollowsRepository userFollowsRepository;

    public FollowRequestService(FollowRequestRepository followRequestRepository, UserFollowsRepository userFollowsRepository) {
        this.followRequestRepository = followRequestRepository;
        this.userFollowsRepository = userFollowsRepository;
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


    public List<FollowRequestResponseDto> getPendingForTarget(String targetId) {
        Long id = Long.parseLong(targetId);
        List<FollowRequest> pendingForTarget = followRequestRepository.findPendingForTarget(id);

        List<FollowRequestResponseDto> responseDtos = new ArrayList<>();
        for (FollowRequest followRequest : pendingForTarget) {
            responseDtos.add(new FollowRequestResponseDto(
                    followRequest.getId(),
                    followRequest.getRequesterId(),
                    followRequest.getTargetId(),
                    followRequest.getCreatedAt()

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
}
