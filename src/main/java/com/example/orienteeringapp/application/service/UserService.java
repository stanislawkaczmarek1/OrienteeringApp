package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
import com.example.orienteeringapp.application.dto.GetUserResponseDto;
import com.example.orienteeringapp.application.dto.UpdateUserDto;
import com.example.orienteeringapp.application.dto.UserDto;
import com.example.orienteeringapp.application.exception.UserNotFoundException;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import org.springframework.stereotype.Service;

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

    public GetUserResponseDto getUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new GetUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isPrivate()
        );
    }

    public GetUserResponseDto getCurrentUser(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        return new GetUserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.isPrivate()
        );
    }

    public GetUserResponseDto updateUser(Long id, UpdateUserDto dto) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (dto.getUsername() != null && !dto.getUsername().equals(existingUser.getUsername())) {
            repository.findByUsername(dto.getUsername()).ifPresent(user -> {
                throw new IllegalArgumentException("Username already exists");
            });
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(existingUser.getEmail())) {
            repository.findByEmail(dto.getEmail()).ifPresent(user -> {
                throw new IllegalArgumentException("Email already exists");
            });
        }

        String passwordHash = existingUser.getPasswordHash();
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            boolean hasExistingPassword = existingUser.getPasswordHash() != null
                    && !existingUser.getPasswordHash().isEmpty();

            if (hasExistingPassword) {
                if (dto.getCurrentPassword() == null || dto.getCurrentPassword().isEmpty()) {
                    throw new IllegalArgumentException("Current password is required to change password");
                }
                if (!passwordHasher.verify(dto.getCurrentPassword(), existingUser.getPasswordHash())) {
                    throw new IllegalArgumentException("Current password is incorrect");
                }
            }

            passwordHash = passwordHasher.hash(dto.getNewPassword());
        }

        User updatedUser = new User(
                existingUser.getId(),
                dto.getUsername() != null ? dto.getUsername() : existingUser.getUsername(),
                dto.getFullName() != null ? dto.getFullName() : existingUser.getFullName(),
                dto.getEmail() != null ? dto.getEmail() : existingUser.getEmail(),
                dto.getPhoneNumber() != null ? dto.getPhoneNumber() : existingUser.getPhoneNumber(),
                passwordHash,
                dto.getIsPrivate() != null ? dto.getIsPrivate() : existingUser.isPrivate(),
                existingUser.getCreatedAt()
        );

        User updated = repository.update(updatedUser);

        return new GetUserResponseDto(
                updated.getId(),
                updated.getUsername(),
                updated.getFullName(),
                updated.getEmail(),
                updated.getPhoneNumber(),
                updated.isPrivate()
        );
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
