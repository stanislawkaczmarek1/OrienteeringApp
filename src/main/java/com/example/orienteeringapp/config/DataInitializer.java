package com.example.orienteeringapp.config;

import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("h2")
public class DataInitializer {

    @Bean
    public CommandLineRunner initTestData(
            UserRepository userRepository,
            PasswordHasher passwordHasher
    ) {
        return args -> {
            if (userRepository.findByUsername("testuser").isPresent()) {
                return;
            }

            User testUser = new User(
                null,
                "testuser",
                "Test User",
                "test@example.com",
                "+1234567890",
                passwordHasher.hash("password123"),
                false,
                null
            );

            userRepository.save(testUser);

            System.out.println("Test user created: testuser/password123");
        };
    }
}
