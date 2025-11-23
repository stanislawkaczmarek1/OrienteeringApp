package com.example.orienteeringapp.config;

import com.example.orienteeringapp.domain.model.Activity;
import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("h2")
public class DataInitializer {

    @Bean
    public CommandLineRunner initTestData(
        UserRepository userRepository,
        PasswordHasher passwordHasher,
        MapRepository mapRepository,
        ActivityRepository activityRepository
    ) {
        return args -> {
            User testUser;
            if (userRepository.findByUsername("testuser").isEmpty()) {
                testUser = new User(
                    null,
                    "testuser",
                    "Test User",
                    "test@example.com",
                    "+1234567890",
                    passwordHasher.hash("password123"),
                    false,
                    null
                );
                testUser = userRepository.save(testUser);
                System.out.println("Test user created: testuser/password123");
            } else {
                testUser = userRepository.findByUsername("testuser").get();
                System.out.println("Test user already exists");
            }

            // Check if maps already exist
            if (!mapRepository.findByUserId(testUser.getId()).isEmpty()) {
                System.out.println("Test maps and activities already exist");
                return;
            }

            // Create Map #1 - Forest Trail
            Map.MapData forestMapData = new Map.MapData(List.of(
                new Map.ControlPoint(45.4215, -75.6972, 1),
                new Map.ControlPoint(45.4225, -75.6982, 2),
                new Map.ControlPoint(45.4235, -75.6992, 3)
            ));

            Map forestMap = new Map(
                null,
                testUser.getId(),
                "Forest Trail",
                "Beautiful forest orienteering trail with varied terrain",
                "Pine Forest National Park",
                forestMapData,
                null
            );
            forestMap = mapRepository.save(forestMap);
            System.out.println("Created map: Forest Trail (ID: " + forestMap.getId() + ")");

            // Create Map #2 - Mountain Ridge
            Map.MapData mountainMapData = new Map.MapData(List.of(
                new Map.ControlPoint(46.5215, -76.7972, 1),
                new Map.ControlPoint(46.5225, -76.7982, 2),
                new Map.ControlPoint(46.5235, -76.7992, 3),
                new Map.ControlPoint(46.5245, -76.8002, 4)
            ));

            Map mountainMap = new Map(
                null,
                testUser.getId(),
                "Mountain Ridge",
                "Challenging mountain terrain with steep elevation changes",
                "Blue Ridge Mountains",
                mountainMapData,
                null
            );
            mountainMap = mapRepository.save(mountainMap);
            System.out.println("Created map: Mountain Ridge (ID: " + mountainMap.getId() + ")");

            // Create Activity #1 for Forest Trail
            List<Activity.PathPoint> activity1Path = new ArrayList<>();
            activity1Path.add(new Activity.PathPoint(45.4215, -75.6972, LocalDateTime.of(2024, 11, 22, 8, 30)));
            activity1Path.add(new Activity.PathPoint(45.4220, -75.6977, LocalDateTime.of(2024, 11, 22, 8, 35)));
            activity1Path.add(new Activity.PathPoint(45.4225, -75.6982, LocalDateTime.of(2024, 11, 22, 8, 40)));
            activity1Path.add(new Activity.PathPoint(45.4235, -75.6992, LocalDateTime.of(2024, 11, 22, 9, 15, 30)));

            Activity activity1 = new Activity(
                null,
                testUser.getId(),
                forestMap.getId(),
                "Morning Forest Run",
                LocalDateTime.of(2024, 11, 22, 8, 30),
                Duration.ofMinutes(45).plusSeconds(30),
                new BigDecimal("5.2"),
                activity1Path,
                null
            );
            activityRepository.save(activity1);
            System.out.println("Created activity: Morning Forest Run");

            // Create Activity #2 for Forest Trail
            List<Activity.PathPoint> activity2Path = new ArrayList<>();
            activity2Path.add(new Activity.PathPoint(45.4215, -75.6972, LocalDateTime.of(2024, 11, 22, 17, 0)));
            activity2Path.add(new Activity.PathPoint(45.4218, -75.6975, LocalDateTime.of(2024, 11, 22, 17, 10)));
            activity2Path.add(new Activity.PathPoint(45.4225, -75.6982, LocalDateTime.of(2024, 11, 22, 17, 30)));
            activity2Path.add(new Activity.PathPoint(45.4230, -75.6987, LocalDateTime.of(2024, 11, 22, 18, 0)));
            activity2Path.add(new Activity.PathPoint(45.4235, -75.6992, LocalDateTime.of(2024, 11, 22, 18, 15)));

            Activity activity2 = new Activity(
                null,
                testUser.getId(),
                forestMap.getId(),
                "Evening Training Session",
                LocalDateTime.of(2024, 11, 22, 17, 0),
                Duration.ofHours(1).plusMinutes(15),
                new BigDecimal("7.8"),
                activity2Path,
                null
            );
            activityRepository.save(activity2);
            System.out.println("Created activity: Evening Training Session");

            // Create Activity #3 for Mountain Ridge
            List<Activity.PathPoint> activity3Path = new ArrayList<>();
            activity3Path.add(new Activity.PathPoint(46.5215, -76.7972, LocalDateTime.of(2024, 11, 21, 10, 0)));
            activity3Path.add(new Activity.PathPoint(46.5220, -76.7977, LocalDateTime.of(2024, 11, 21, 10, 30)));
            activity3Path.add(new Activity.PathPoint(46.5225, -76.7982, LocalDateTime.of(2024, 11, 21, 11, 0)));
            activity3Path.add(new Activity.PathPoint(46.5235, -76.7992, LocalDateTime.of(2024, 11, 21, 11, 45)));
            activity3Path.add(new Activity.PathPoint(46.5245, -76.8002, LocalDateTime.of(2024, 11, 21, 12, 30)));

            Activity activity3 = new Activity(
                null,
                testUser.getId(),
                mountainMap.getId(),
                "Mountain Challenge",
                LocalDateTime.of(2024, 11, 21, 10, 0),
                Duration.ofHours(2).plusMinutes(30),
                new BigDecimal("12.5"),
                activity3Path,
                null
            );
            activityRepository.save(activity3);
            System.out.println("Created activity: Mountain Challenge");

            // Create Activity #4 for Mountain Ridge
            List<Activity.PathPoint> activity4Path = new ArrayList<>();
            activity4Path.add(new Activity.PathPoint(46.5215, -76.7972, LocalDateTime.of(2024, 11, 20, 14, 30)));
            activity4Path.add(new Activity.PathPoint(46.5225, -76.7982, LocalDateTime.of(2024, 11, 20, 14, 50)));
            activity4Path.add(new Activity.PathPoint(46.5235, -76.7992, LocalDateTime.of(2024, 11, 20, 15, 25)));

            Activity activity4 = new Activity(
                null,
                testUser.getId(),
                mountainMap.getId(),
                "Speed Training",
                LocalDateTime.of(2024, 11, 20, 14, 30),
                Duration.ofMinutes(55),
                new BigDecimal("6.3"),
                activity4Path,
                null
            );
            activityRepository.save(activity4);
            System.out.println("Created activity: Speed Training");

            // Create Activity #5 for Mountain Ridge
            List<Activity.PathPoint> activity5Path = new ArrayList<>();
            activity5Path.add(new Activity.PathPoint(46.5215, -76.7972, LocalDateTime.of(2024, 11, 19, 9, 0)));
            activity5Path.add(new Activity.PathPoint(46.5220, -76.7977, LocalDateTime.of(2024, 11, 19, 9, 45)));
            activity5Path.add(new Activity.PathPoint(46.5225, -76.7982, LocalDateTime.of(2024, 11, 19, 10, 30)));
            activity5Path.add(new Activity.PathPoint(46.5230, -76.7987, LocalDateTime.of(2024, 11, 19, 11, 15)));
            activity5Path.add(new Activity.PathPoint(46.5235, -76.7992, LocalDateTime.of(2024, 11, 19, 11, 45)));
            activity5Path.add(new Activity.PathPoint(46.5245, -76.8002, LocalDateTime.of(2024, 11, 19, 12, 10)));

            Activity activity5 = new Activity(
                null,
                testUser.getId(),
                mountainMap.getId(),
                "Endurance Test",
                LocalDateTime.of(2024, 11, 19, 9, 0),
                Duration.ofHours(3).plusMinutes(10),
                new BigDecimal("18.7"),
                activity5Path,
                null
            );
            activityRepository.save(activity5);
            System.out.println("Created activity: Endurance Test");

            System.out.println("Test data initialization complete!");
            System.out.println("- 2 maps created");
            System.out.println("- 5 activities created (2 for Forest Trail, 3 for Mountain Ridge)");
        };
    }
}
