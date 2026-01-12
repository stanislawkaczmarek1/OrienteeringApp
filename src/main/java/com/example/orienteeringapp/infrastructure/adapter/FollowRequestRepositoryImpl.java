package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import com.example.orienteeringapp.infrastructure.entity.FollowRequestEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaFollowRequestRepository;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FollowRequestRepositoryImpl implements FollowRequestRepository {
    private final JpaFollowRequestRepository jpaFollowRequestRepository;
    private final JpaUserRepository jpaUserRepository;

    public FollowRequestRepositoryImpl(JpaFollowRequestRepository jpaFollowRequestRepository, JpaUserRepository jpaUserRepository) {
        this.jpaFollowRequestRepository = jpaFollowRequestRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public FollowRequest save(FollowRequest followRequest) {
        FollowRequestEntity entity = new FollowRequestEntity();

        UserEntity requester = jpaUserRepository.findById(followRequest.getRequesterId())
                .orElseThrow(() -> new RuntimeException("Requester not found"));
        entity.setRequester(requester);

        UserEntity target = jpaUserRepository.findById(followRequest.getTargetId())
                .orElseThrow(() -> new RuntimeException("Target user not found"));
        entity.setTarget(target);

        FollowRequestEntity saved = jpaFollowRequestRepository.save(entity);
        return followRequestEntityToDomain(saved);
    }

    @Override
    public List<FollowRequest> findPendingForTarget(Long targetId) {
        List<FollowRequestEntity> entities = jpaFollowRequestRepository.findByTarget_Id(targetId);
        return entities.stream()
                .map(this::followRequestEntityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long requestId) {
        jpaFollowRequestRepository.deleteById(requestId);
    }

    @Override
    public Optional<FollowRequest> findByRequesterAndTarget(Long requesterId, Long targetId) {
        return jpaFollowRequestRepository.findByRequester_IdAndTarget_Id(requesterId, targetId)
                .map(this::followRequestEntityToDomain);
    }

    @Override
    public void deleteByRequesterAndTarget(Long requesterId, Long targetId) {
        jpaFollowRequestRepository.deleteByRequesterIdAndTargetId(requesterId, targetId);
    }

    @Override
    public Optional<FollowRequest> findById(Long id) {
        return jpaFollowRequestRepository.findById(id)
                .map(this::followRequestEntityToDomain);
    }

    private FollowRequest followRequestEntityToDomain(FollowRequestEntity entity) {
        return new FollowRequest(
                entity.getId(),
                entity.getRequester().getId(),
                entity.getTarget().getId(),
                entity.getCreatedAt()
        );
    }
}
