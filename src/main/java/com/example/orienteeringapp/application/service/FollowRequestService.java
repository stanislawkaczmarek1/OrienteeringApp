package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.FollowRequestResponseDto;
import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.model.enums.FollowRequestStatus;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowRequestService {
    private final FollowRequestRepository repository;

    public FollowRequestService(FollowRequestRepository repository) {
        this.repository = repository;
    }

    public FollowRequestResponseDto createFollowRequest(CreateFollowRequestDto dto) {
        FollowRequest request = new FollowRequest(
                null,
                dto.getRequesterId(),
                dto.getTargetId(),
                FollowRequestStatus.PENDING,
                null
        );

        FollowRequest created = repository.save(request);

        return new FollowRequestResponseDto(created.getId(),
                created.getRequesterId(),
                created.getTargetId(),
                created.getStatus(),
                created.getCreatedAt());
    }


    public void deleteFollowRequest(Long id) {
        repository.deleteById(id);
    }

    public FollowRequestResponseDto getById(Long id) {
        return null;
    }

    public List<FollowRequestResponseDto> getPendingForTarget(Long id) {
        return null;
    }

    public FollowRequestResponseDto getByRequesterAndTarget(Long requesterId, Long targetId) {
        return null;
    }

    public void acceptRequest(Long id) {
    }

    public void rejectRequest(Long id) {
    }
}
