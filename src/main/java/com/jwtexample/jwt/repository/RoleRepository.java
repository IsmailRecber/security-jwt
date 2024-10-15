package com.jwtexample.jwt.repository;

import com.jwtexample.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}