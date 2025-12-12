package com.example.orienteeringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OrienteeringApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrienteeringApplication.class, args);
	}
}
