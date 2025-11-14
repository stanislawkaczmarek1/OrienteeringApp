package com.example.orienteeringapp.infrastructure.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@ownershipChecker.isMapOwner(#id, authentication.name)")
@SuppressWarnings("SpringElInspection")
public @interface IsMapOwner {
}
