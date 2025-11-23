package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateFollowRequestDto;
import com.example.orienteeringapp.application.dto.CreateFollowRequestResponseDto;
import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.model.enums.FollowRequestStatus;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowRequestService {
    private final FollowRequestRepository repository;

    public FollowRequestService(FollowRequestRepository repository) {
        this.repository = repository;
    }

    public CreateFollowRequestResponseDto createFollowRequest(CreateFollowRequestDto dto) {
        FollowRequest request = new FollowRequest(
                null,
                dto.getRequesterId(),
                dto.getTargetId(),
                FollowRequestStatus.PENDING,
                null
        );

        FollowRequest created = repository.save(request);

        return new CreateFollowRequestResponseDto(created.getId());
    }


    public void deleteFollowRequest(Long id) {
        repository.deleteById(id);
    }
}
