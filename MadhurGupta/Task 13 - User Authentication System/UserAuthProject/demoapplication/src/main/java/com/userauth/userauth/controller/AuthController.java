package com.userauth.userauth.controller;

import com.userauth.userauth.dto.AuthRequest;
import com.userauth.userauth.dto.AuthResponse;
import com.userauth.userauth.config.JwtUtil;
import com.userauth.userauth.service.impl.CustomUserDetailServiceImpl;
import com.userauth.userauth.service.ServiceInterface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;  // Use interface instead of impl
    private final CustomUserDetailServiceImpl customUserDetailServiceImpl;
    private final JwtUtil jwtUtil; // Inject JwtUtil as a Spring bean

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest request) {
        try {
            if (userService.userExists(request.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new AuthResponse(null, true, "Username is already taken"));
            }

            // Save user with encoded password inside service
            userService.saveUser(request);

            // Authenticate to confirm credentials and generate token
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = customUserDetailServiceImpl.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token, false, null));
        } catch (Exception e) {
            log.error("Signup error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse(null, true, "An error occurred during signup"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, true, "Invalid username or password"));
        }

        UserDetails userDetails = customUserDetailServiceImpl.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, false, null));
    }
}
