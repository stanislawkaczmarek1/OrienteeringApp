package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.GetUserResponseDto;
import com.example.orienteeringapp.application.exception.UserNotFoundException;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
import com.example.orienteeringapp.application.dto.UserDto;
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
        String hashed = passwordHasher.hash(dto.password());

        User user = new User(null, dto.username(),
                dto.fullName(),
                dto.email(),
                dto.phoneNumber(),
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
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.isPrivate());
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
