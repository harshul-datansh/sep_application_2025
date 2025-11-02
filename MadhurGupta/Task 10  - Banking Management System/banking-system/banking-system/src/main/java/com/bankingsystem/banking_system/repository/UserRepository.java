package com.bankingsystem.banking_system.repository;

import com.bankingsystem.banking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   // Used for login
    Optional<User> findByEmail(String email);

   // used during registration to avoid duplicates
    boolean existsByEmail(String email);

}
