package com.example.orienteeringapp.infrastructure.jobs;

import com.example.orienteeringapp.domain.repository.RefreshTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DeleteExpiredTokensJob {

    private final RefreshTokenRepository tokenRepository;

    public DeleteExpiredTokensJob(RefreshTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // runs every day at 3:00am
    @Scheduled(cron = "0 0 3 * * *")
    public void cleanExpiredTokens() {
        Instant now = Instant.now();
        tokenRepository.deleteAllExpiredSince(now);
    }
}
