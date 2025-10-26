package com.userauth.userauth.service.oauthservice;

import com.userauth.userauth.entity.Role;
import com.userauth.userauth.entity.User;
import com.userauth.userauth.entity.UserRole;
import com.userauth.userauth.repository.RoleRepository;
import com.userauth.userauth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomOAuth2UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Optional<User> optionalUser = userRepository.findByUsername(email);

        User user = optionalUser.orElseGet(() -> {
            User newUser = new User();
            newUser.setUsername(email);
            newUser.setPassword(""); // No password for OAuth2 user

            // Assign default role (ROLE_USER)
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_USER");
                        return roleRepository.save(role);
                    });

            UserRole userRole = new UserRole();
            userRole.setUser(newUser);
            userRole.setRole(defaultRole);

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(userRole);
            newUser.setUserRoles(userRoles);

            return userRepository.save(newUser);
        });

        return new CustomUserPrincipal(user, oAuth2User.getAttributes());
    }
}
