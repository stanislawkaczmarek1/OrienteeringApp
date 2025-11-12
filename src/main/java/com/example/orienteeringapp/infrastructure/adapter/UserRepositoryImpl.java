package com.example.orienteeringapp.infrastructure.adapter;

import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
import com.example.orienteeringapp.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();

        entity.setUsername(user.getUsername());
        entity.setFullName(user.getFullName());
        entity.setEmail(user.getEmail());
        entity.setPhoneNumber(user.getPhoneNumber());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setPrivate(user.isPrivate());

        UserEntity saved = jpaUserRepository.save(entity);
        return new User(saved.getId(), saved.getUsername(), saved.getFullName(),
                saved.getEmail(), saved.getPhoneNumber(),
                saved.getPasswordHash(), saved.isPrivate(), saved.getCreatedAt());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }//todo

    @Override
    public void delete(Long id) {

    }//todo
}

