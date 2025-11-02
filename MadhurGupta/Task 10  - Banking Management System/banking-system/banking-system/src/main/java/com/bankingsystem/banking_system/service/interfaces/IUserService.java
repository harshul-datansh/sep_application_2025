package com.bankingsystem.banking_system.service.interfaces;

import com.bankingsystem.banking_system.dto.user.LoginResponseDTO;

import com.bankingsystem.banking_system.dto.user.UserRequestDTO;
import com.bankingsystem.banking_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    // Register a new user and return the saved entity
    User registerUser(UserRequestDTO userRegisterRequestDTO);
    LoginResponseDTO refreshAccessToken(String refreshToken);

    // Authenticate user and return User (or JWT token later)
    LoginResponseDTO loginUser(String email, String password);

    // Get a user by email
    Optional<User> getUserByEmail(String email);

    // Get a user by ID
    Optional<User> getUserById(Long id);

    // (Optional) Get all users for admin
    List<User> getAllUsers();
}
