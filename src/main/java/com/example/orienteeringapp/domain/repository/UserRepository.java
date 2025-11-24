package com.example.orienteeringapp.domain.repository;

import com.example.orienteeringapp.domain.model.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    void delete(Long id);
}
