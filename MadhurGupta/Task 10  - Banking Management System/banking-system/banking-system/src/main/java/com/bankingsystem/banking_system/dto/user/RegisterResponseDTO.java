package com.bankingsystem.banking_system.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {

    private String message;  // Success message
    private Long userId;     // ID of newly registered user
    private String email;    // Registered email
}
