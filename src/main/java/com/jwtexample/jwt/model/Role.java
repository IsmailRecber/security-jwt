package com.jwtexample.jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {}

    public Role(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }


}