package com.example.orienteeringapp.config;

import com.example.orienteeringapp.domain.model.Activity;
import com.example.orienteeringapp.domain.model.Map;
import com.example.orienteeringapp.domain.model.Post;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.model.enums.PostVisibility;
import com.example.orienteeringapp.domain.repository.ActivityRepository;
import com.example.orienteeringapp.domain.repository.MapRepository;
import com.example.orienteeringapp.domain.repository.PostRepository;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import com.example.orienteeringapp.infrastructure.entity.ActivityEntity;
import com.example.orienteeringapp.infrastructure.entity.PostEntity;
import com.example.orienteeringapp.infrastructure.entity.UserEntity;
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
        ActivityRepository activityRepository,
        PostRepository postRepository
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

            User testUser2;
            if (userRepository.findByUsername("publicuser").isEmpty()) {
                testUser2 = new User(
                        null,
                        "publicuser",
                        "Public User",
                        "public@example.com",
                        "+1234567891",
                        passwordHasher.hash("password123"),
                        false,
                        null
                );
                testUser2 = userRepository.save(testUser2);
                System.out.println("Test user 2 created: publicuser/password123 (public)");
            } else {
                testUser2 = userRepository.findByUsername("publicuser").get();
                System.out.println("Public user already exists");
            }

            User testUser3;
            if (userRepository.findByUsername("privateuser").isEmpty()) {
                testUser3 = new User(
                        null,
                        "privateuser",
                        "Private User",
                        "private@example.com",
                        "+1234567892",
                        passwordHasher.hash("password123"),
                        true,
                        null
                );
                testUser3 = userRepository.save(testUser3);
                System.out.println("Test user 3 created: privateuser/password123 (private)");
            } else {
                testUser3 = userRepository.findByUsername("privateuser").get();
                System.out.println("Private user already exists");
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
            Activity savedActivity1 = activityRepository.save(activity1);
            System.out.println("Activity ID: " + savedActivity1.getId());
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
            Activity savedActivity2 = activityRepository.save(activity2);
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
            Activity savedActivity3 = activityRepository.save(activity3);
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
            Activity savedActivity4 = activityRepository.save(activity4);
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
            Activity savedActivity5 = activityRepository.save(activity5);

            // Create Map #3 - Wrocław City Orienteering
            Map.MapData wroclawMapData = new Map.MapData(List.of(
                new Map.ControlPoint(51.1100, 17.0320, 1),  // Start: Old Town Market Square area
                new Map.ControlPoint(51.1140, 17.0355, 2),  // Near University
                new Map.ControlPoint(51.1130, 17.0445, 3),  // Botanical Garden entrance
                new Map.ControlPoint(51.1145, 17.0470, 4),  // Ostrów Tumski (Cathedral Island)
                new Map.ControlPoint(51.1175, 17.0385, 5),  // Słodowa Island Park
                new Map.ControlPoint(51.1080, 17.0650, 6),  // Park Szczytnicki west
                new Map.ControlPoint(51.1060, 17.0765, 7),  // Centennial Hall area
                new Map.ControlPoint(51.1080, 17.0830, 8)   // Finish: Zoo area
            ));

            Map wroclawMap = new Map(
                null,
                testUser.getId(),
                "Wrocław City Orienteering",
                "Urban orienteering through historic Wrocław - from Old Town through Cathedral Island to Centennial Hall area. Challenging navigation through parks and historic districts.",
                "Wrocław, Poland",
                wroclawMapData,
                null
            );
            wroclawMap = mapRepository.save(wroclawMap);
            System.out.println("Created map: Wrocław City Orienteering (ID: " + wroclawMap.getId() + ")");

            // Create Activity for Wrocław City Orienteering - detailed path data every 5 seconds
            // Total course: ~10km, estimated time: ~75 minutes at varying pace
            List<Activity.PathPoint> wroclawActivityPath = new ArrayList<>();
            LocalDateTime startTime = LocalDateTime.of(2024, 11, 23, 9, 0, 0);

            // Helper variables for realistic GPS simulation
            java.util.Random rand = new java.util.Random(42); // Seeded for reproducibility

            // Segment 1: Start (51.1100, 17.0320) to Control 2 (51.1140, 17.0355) - ~550m, ~4 min
            // Fresh start, good pace
            double lat = 51.1100, lon = 17.0320;
            int seconds = 0;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));
            // Running north-northeast through old town streets
            for (int i = 1; i <= 48; i++) { // 48 x 5s = 4 min
                seconds += 5;
                lat += 0.000083 + (rand.nextDouble() - 0.5) * 0.00003;
                lon += 0.000073 + (rand.nextDouble() - 0.5) * 0.00003;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            // Arrive at Control 2
            lat = 51.1140; lon = 17.0355;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 2: Control 2 (51.1140, 17.0355) to Control 3 (51.1130, 17.0445) - ~650m, ~5 min
            // Heading east towards Botanical Garden
            for (int i = 1; i <= 59; i++) { // ~5 min
                seconds += 5;
                lat += -0.000017 + (rand.nextDouble() - 0.5) * 0.00004;
                lon += 0.000152 + (rand.nextDouble() - 0.5) * 0.00004;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            lat = 51.1130; lon = 17.0445;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 3: Control 3 (51.1130, 17.0445) to Control 4 (51.1145, 17.0470) - ~220m, ~2 min
            // Short segment to Cathedral Island
            for (int i = 1; i <= 23; i++) {
                seconds += 5;
                lat += 0.000065 + (rand.nextDouble() - 0.5) * 0.00002;
                lon += 0.000109 + (rand.nextDouble() - 0.5) * 0.00002;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            lat = 51.1145; lon = 17.0470;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 4: Control 4 (51.1145, 17.0470) to Control 5 (51.1175, 17.0385) - ~700m, ~6 min
            // Heading northwest to Słodowa Island, crossing bridges
            for (int i = 1; i <= 71; i++) {
                seconds += 5;
                lat += 0.000042 + (rand.nextDouble() - 0.5) * 0.00005;
                lon += -0.000120 + (rand.nextDouble() - 0.5) * 0.00005;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            lat = 51.1175; lon = 17.0385;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 5: Control 5 (51.1175, 17.0385) to Control 6 (51.1080, 17.0650) - ~2.2km, ~16 min
            // Long segment heading southeast through streets to Park Szczytnicki, pace slowing
            for (int i = 1; i <= 191; i++) {
                seconds += 5;
                lat += -0.000050 + (rand.nextDouble() - 0.5) * 0.00006;
                lon += 0.000139 + (rand.nextDouble() - 0.5) * 0.00006;
                // Add some route variations (not straight line)
                if (i > 60 && i < 80) lat += 0.00002; // detour north
                if (i > 120 && i < 140) lon -= 0.00003; // slight westward curve
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            lat = 51.1080; lon = 17.0650;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 6: Control 6 (51.1080, 17.0650) to Control 7 (51.1060, 17.0765) - ~850m, ~7 min
            // Through Park Szczytnicki to Centennial Hall, technical terrain
            for (int i = 1; i <= 83; i++) {
                seconds += 5;
                lat += -0.000024 + (rand.nextDouble() - 0.5) * 0.00005;
                lon += 0.000138 + (rand.nextDouble() - 0.5) * 0.00005;
                // Park paths with slight curves
                if (i > 30 && i < 50) lat -= 0.00001;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            lat = 51.1060; lon = 17.0765;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            // Segment 7: Control 7 (51.1060, 17.0765) to Control 8/Finish (51.1080, 17.0830) - ~500m, ~4.5 min
            // Final push to zoo area, tired but finishing strong
            for (int i = 1; i <= 53; i++) {
                seconds += 5;
                lat += 0.000038 + (rand.nextDouble() - 0.5) * 0.00004;
                lon += 0.000123 + (rand.nextDouble() - 0.5) * 0.00004;
                wroclawActivityPath.add(new Activity.PathPoint(
                    Math.round(lat * 100000.0) / 100000.0,
                    Math.round(lon * 100000.0) / 100000.0,
                    startTime.plusSeconds(seconds)
                ));
            }
            // Final arrival at finish
            lat = 51.1080; lon = 17.0830;
            seconds += 5;
            wroclawActivityPath.add(new Activity.PathPoint(lat, lon, startTime.plusSeconds(seconds)));

            Activity wroclawActivity = new Activity(
                null,
                testUser.getId(),
                wroclawMap.getId(),
                "Wrocław Urban Adventure",
                startTime,
                Duration.ofSeconds(seconds),
                new BigDecimal("9.8"),
                wroclawActivityPath,
                null
            );
            Activity savedWrclawActivity=  activityRepository.save(wroclawActivity);
            System.out.println("Created activity: Wrocław Urban Adventure (" + wroclawActivityPath.size() + " path points, " + (seconds / 60) + " minutes)");


            // Update the final summary
            System.out.println("Test data initialization complete!");
            System.out.println("- 3 maps created");
            System.out.println("- 6 activities created (2 for Forest Trail, 3 for Mountain Ridge, 1 for Wrocław)");
        };
    }
}
