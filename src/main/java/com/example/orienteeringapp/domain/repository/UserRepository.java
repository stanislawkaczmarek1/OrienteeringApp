package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    void delete(Long id);
}
