package com.jwtexample.jwt.repository;

import com.jwtexample.jwt.model.Role;
import com.jwtexample.jwt.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}