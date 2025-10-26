package com.userauth.userauth.config;

import com.userauth.userauth.entity.Role;
import com.userauth.userauth.entity.User;
import com.userauth.userauth.repository.RoleRepository;
import com.userauth.userauth.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();
        String email = null;

        if (principal instanceof DefaultOidcUser oidcUser) {
            email = oidcUser.getEmail();
        } else if (principal instanceof DefaultOAuth2User oauthUser) {
            Map<String, Object> attributes = oauthUser.getAttributes();
            email = (String) attributes.get("email");
        } else {
            throw new IllegalStateException("Unexpected user principal type: " + principal.getClass());
        }

        System.out.println("OAuth2 login success. Email: " + email);

        // 1. Check if user exists
        Optional<User> existingUser = userRepository.findByUsername(email);

        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            // 2. If not, create and save a new user with default role
            user = new User();
            user.setUsername(email);
            user.setPassword("OAUTH2_USER"); // Not used, just required field

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));

            user.addRole(userRole);
            userRepository.save(user);
            System.out.println("New user created: " + email);
        }

        // 3. Generate JWT
        String token = jwtUtil.generateToken(user.getUsername());
        System.out.println("Generated JWT Token: " + token);

        // 4. Redirect with token (or send as cookie, etc.)
        //response.sendRedirect("http://localhost:3000/dashboard?token=" + token);
        response.setHeader("Authorization", "Bearer " + token);
        log.info("final headers: {}", response.getHeader("Authorization"));
    }
}
