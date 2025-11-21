package com.example.orienteeringapp.infrastructure.entity.keys;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserFollowsId implements Serializable {
    private Long followerId;
    private Long followingId;
}
