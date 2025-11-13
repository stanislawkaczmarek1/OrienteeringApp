package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
import com.example.orienteeringapp.application.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordHasher passwordHasher;

    public UserService(UserRepository repository, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    public CreateUserResponseDto createUser(CreateUserDto dto) {
        String hashed = passwordHasher.hash(dto.getPassword());

        User user = new User(
                null,
                dto.getUsername(),
                dto.getFullName(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                hashed,
                dto.isPrivate(),
                null
        );
        User created = repository.save(user);
        return new CreateUserResponseDto(created.getId());
    }

    public Optional<UserDto> getUser(Long id) {
        return repository.findById(id)
                .map(this::userToDto);
    }

    public void deleteUser(Long id) {
        repository.delete(id);
    }

    private UserDto userToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isPrivate(),
                user.getCreatedAt()
        );
    }
}
