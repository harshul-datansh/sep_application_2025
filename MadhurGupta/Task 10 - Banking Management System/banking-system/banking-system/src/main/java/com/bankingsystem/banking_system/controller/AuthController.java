package com.bankingsystem.banking_system.controller;

import com.bankingsystem.banking_system.dto.user.LoginResponseDTO;
import com.bankingsystem.banking_system.dto.user.RegisterResponseDTO;
import com.bankingsystem.banking_system.dto.user.UserRequestDTO;
import com.bankingsystem.banking_system.entity.User;
import com.bankingsystem.banking_system.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final UserServiceImpl userService;

    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account with the provided email and password.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User registered successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RegisterResponseDTO.class),
                                    examples = @ExampleObject(value = "{ \"message\": \"User registered successfully\", \"userId\": \"123\", \"email\": \"user@example.com\" }")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request or user already exists"
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        User savedUser = userService.registerUser(userRequestDTO);

        RegisterResponseDTO response = new RegisterResponseDTO(
                "User registered successfully",
                savedUser.getId(),
                savedUser.getEmail()
        );

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates user and returns access & refresh tokens.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User authenticated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class),
                                    examples = @ExampleObject(value = "{ \"accessToken\": \"jwt_token_here\", \"refreshToken\": \"refresh_token_here\" }")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid credentials"
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserRequestDTO loginRequest) {
        LoginResponseDTO response = userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Refresh JWT access token",
            description = "Generates a new access token using a valid refresh token.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Access token refreshed successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class),
                                    examples = @ExampleObject(value = "{ \"accessToken\": \"new_jwt_token\", \"refreshToken\": \"same_refresh_token\" }")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid or missing refresh token"
                    )
            }
    )
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        LoginResponseDTO response = userService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(response);
    }
}
