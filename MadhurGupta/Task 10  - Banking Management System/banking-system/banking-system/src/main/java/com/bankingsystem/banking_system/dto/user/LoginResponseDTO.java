package com.bankingsystem.banking_system.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponseDTO {

    private Long userId;               // Logged in user ID
    private String name;               // User name
    private String email;              // User email
    private List<String> roles;        // Roles assigned to the user
    private String accessToken;        // JWT access token
    private String refreshToken;       // Refresh token for renewing access
}
