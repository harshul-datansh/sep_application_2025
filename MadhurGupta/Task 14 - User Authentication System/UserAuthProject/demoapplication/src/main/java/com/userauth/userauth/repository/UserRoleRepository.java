package com.userauth.userauth.repository;

import com.userauth.userauth.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole , Long> {
    List<UserRole> findByUserUsername(String username);
}
