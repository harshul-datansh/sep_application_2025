package com.userauth.userauth.repository;

import com.userauth.userauth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission , Long> {
    Optional<Permission> findByName(String name);

}
