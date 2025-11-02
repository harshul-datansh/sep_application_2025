package com.bankingsystem.banking_system.service.impl;

import com.bankingsystem.banking_system.dto.user.LoginResponseDTO;
import com.bankingsystem.banking_system.dto.user.UserRequestDTO;
import com.bankingsystem.banking_system.entity.RefreshToken;
import com.bankingsystem.banking_system.entity.Role;
import com.bankingsystem.banking_system.entity.User;
import com.bankingsystem.banking_system.entity.UserRole;
import com.bankingsystem.banking_system.exception.ConflictException;
import com.bankingsystem.banking_system.exception.ValidationException;
import com.bankingsystem.banking_system.repository.RefreshTokenRepository;
import com.bankingsystem.banking_system.repository.RoleRepository;
import com.bankingsystem.banking_system.repository.UserRepository;
import com.bankingsystem.banking_system.service.interfaces.IUserService;
import com.bankingsystem.banking_system.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /** Register a new customer user */
    @Override
    @Transactional
    public User registerUser(UserRequestDTO userRequestDTO) {

        if (userRequestDTO == null) {
            throw new ValidationException("User details cannot be null");
        }

        String name = userRequestDTO.getName();
        String email = userRequestDTO.getEmail();
        String password = userRequestDTO.getPassword();

        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }

        email = email.trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("Email already registered");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Role customerRole = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new ValidationException("Default CUSTOMER role not found in database"));

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(customerRole);

        user.getUserRoles().add(userRole);

        userRepository.save(user);

        log.info("New user registered successfully with email: {}", email);

        return user;
    }

    @Override
    @Transactional
    public LoginResponseDTO refreshAccessToken(String refreshToken) {
        // delegate to JwtUtil
        return jwtUtil.refreshAccessToken(refreshToken);
    }

    /** Login user and generate JWT + refresh token */
    @Override
    @Transactional
    public LoginResponseDTO loginUser(String email, String password) {

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            throw new ValidationException("Email and password are required");
        }

        email = email.trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ValidationException("Invalid email or password");
        }

        // Generate JWT access token
        String accessToken = jwtUtil.generateAccessToken(user);

        // Generate refresh token
        RefreshToken refreshToken = jwtUtil.generateRefreshToken(user);

        List<String> roles = user.getUserRoles().stream()
                .map(ur -> ur.getRole().getRoleName())
                .collect(Collectors.toList());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRoles(roles);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken.getToken());

        log.info("User logged in successfully: {}", email);

        return response;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
