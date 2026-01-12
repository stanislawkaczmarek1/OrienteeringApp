package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowRequestService {
    private final FollowRequestRepository repository;

    public FollowRequestService(FollowRequestRepository repository) {
        this.repository = repository;
    }

    public FollowRequestResponseDto createFollowRequest(CreateFollowRequestDto dto, String requesterId) {
        Long id = Long.parseLong(requesterId);
        FollowRequest request = new FollowRequest(
                null,
                id,
                dto.getTargetId(),
                null
        );

        FollowRequest created = repository.save(request);

        return new FollowRequestResponseDto(created.getId(),
                created.getRequesterId(),
                created.getTargetId(),
                created.getCreatedAt());
    }


    public List<FollowRequestResponseDto> getPendingForTarget(String id) {
        return new ArrayList<>();
    }

    public Boolean existsByRequesterAndTarget(String requesterId, Long targetId) {
        return false;
    }

    public void acceptRequest(Long id) {
    }

    public void rejectRequest(Long id) {
    }
}
