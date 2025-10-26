package com.bankingsystem.banking_system.repository;

import com.bankingsystem.banking_system.entity.RefreshToken;
import com.bankingsystem.banking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // Find refresh token by token string
    Optional<RefreshToken> findByToken(String token);

    // Optional: find all refresh tokens for a specific user
    List<RefreshToken> findAllByUser(User user);

    // Optional: delete all tokens of a user (useful for logout)
    void deleteAllByUser(User user);
}
