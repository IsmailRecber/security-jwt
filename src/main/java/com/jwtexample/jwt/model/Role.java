package com.jwtexample.jwt.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}