package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.FollowRequest;
import com.example.orienteeringapp.domain.repository.FollowRequestRepository;
import com.example.orienteeringapp.infrastructure.entity.FollowRequestEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaFollowRequestRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FollowRequestRepositoryImpl implements FollowRequestRepository {
    private final JpaFollowRequestRepository jpaFollowRequestRepository;

    public FollowRequestRepositoryImpl(JpaFollowRequestRepository jpaFollowRequestRepository) {
        this.jpaFollowRequestRepository = jpaFollowRequestRepository;
    }

    @Override
    public FollowRequest save(FollowRequest followRequest) {
        FollowRequestEntity entity = new FollowRequestEntity();

        entity.setId(followRequest.getId());

        UserEntity requester = new UserEntity();
        requester.setId(followRequest.getRequesterId());
        entity.setRequester(requester);

        UserEntity target = new UserEntity();
        target.setId(followRequest.getTargetId());
        entity.setTarget(target);

        entity.setStatus(followRequest.getStatus());


        FollowRequestEntity saved = jpaFollowRequestRepository.save(entity);
        return followRequestEntityToDomain(saved);
    }

    @Override
    public Optional<FollowRequest> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<FollowRequest> findPendingForTarget(Long targetId) {
        return null;
    }

    @Override
    public FollowRequest acceptRequest(Long requestId) {
        return null;
    }

    @Override
    public FollowRequest rejectRequest(Long requestId) {
        return null;
    }

    @Override
    public FollowRequest findByRequesterAndTarget(Long requesterId, Long targetId) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        jpaFollowRequestRepository.deleteById(id);
    }

    private FollowRequest followRequestEntityToDomain(FollowRequestEntity entity) {
        return new FollowRequest(
                entity.getId(),
                entity.getRequester().getId(),
                entity.getTarget().getId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
