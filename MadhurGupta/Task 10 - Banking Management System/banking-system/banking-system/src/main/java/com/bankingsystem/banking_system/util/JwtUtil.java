package com.bankingsystem.banking_system.util;

import com.bankingsystem.banking_system.dto.user.LoginResponseDTO;
import com.bankingsystem.banking_system.entity.RefreshToken;
import com.bankingsystem.banking_system.entity.User;
import com.bankingsystem.banking_system.exception.ValidationException;
import com.bankingsystem.banking_system.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    @Value("${jwt.refreshExpirationMs}")
    private long refreshTokenExpirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    /** Convert secret string to Key object for HS512 */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /** Generate JWT access token */
    public String generateAccessToken(User user) {
        List<String> roles = user.getUserRoles().stream()
                .map(ur -> ur.getRole().getRoleName())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Use Key object
                .compact();
    }

    /** Validate JWT token */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /** Extract user ID from JWT token */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    /** Extract roles from JWT token */
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", List.class);
    }

    /** Generate refresh token for a user */
    public RefreshToken generateRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenExpirationMs / 1000));
        refreshToken.setStatus("ACTIVE");
        return refreshTokenRepository.save(refreshToken);
    }

    /** Extract user ID from Bearer token header */
    public Long getUserIdFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ValidationException("Invalid Authorization header");
        }
        String token = authorizationHeader.substring(7); // Remove "Bearer "
        return getUserIdFromToken(token);
    }

    /** Refresh access token using refresh token string */
    @Transactional
    public LoginResponseDTO refreshAccessToken(String refreshTokenStr) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenStr)
                .orElseThrow(() -> new ValidationException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new ValidationException("Refresh token expired. Please login again.");
        }

        if (!"ACTIVE".equals(refreshToken.getStatus())) {
            throw new ValidationException("Refresh token is no longer active");
        }

        User user = refreshToken.getUser();
        String newAccessToken = generateAccessToken(user);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setUserId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRoles(user.getUserRoles()
                .stream()
                .map(ur -> ur.getRole().getRoleName())
                .collect(Collectors.toList()));
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(refreshTokenStr); // keep same refresh token

        return response;
    }
}
