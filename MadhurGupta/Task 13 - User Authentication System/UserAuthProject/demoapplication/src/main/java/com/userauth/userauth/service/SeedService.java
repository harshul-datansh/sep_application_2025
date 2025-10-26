package com.userauth.userauth.service;

import com.userauth.userauth.entity.*;
import com.userauth.userauth.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeedService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void seedRolesAndPermissions() {
        Role admin = getOrCreateRole("admin");
        Role editor = getOrCreateRole("editor");
        Role viewer = getOrCreateRole("viewer");

        Permission create = getOrCreatePermission("create_post");
        Permission edit = getOrCreatePermission("edit_post");
        Permission delete = getOrCreatePermission("delete_post");
        Permission view = getOrCreatePermission("view_post");

        assignPermission(admin, Set.of(create, edit, delete, view));
        assignPermission(editor, Set.of(create, edit, view));
        assignPermission(viewer, Set.of(view));
    }

    @Transactional
    public void seedAdminUser() {
        String adminUsername = "admin";
        Optional<User> existingUser = userRepository.findByUsername(adminUsername);
        if (existingUser.isPresent()) return;

        User admin = new User();
        admin.setUsername(adminUsername);
        admin.setPassword(passwordEncoder.encode("admin123"));

        Role adminRole = roleRepository.findByName("admin")
                .orElseThrow(() -> new RuntimeException("Admin role not found"));

        UserRole userRole = new UserRole();
        userRole.setUser(admin);
        userRole.setRole(adminRole);

        admin.setUserRoles(Set.of(userRole));
        userRepository.save(admin);
    }

// helper

    private Role getOrCreateRole(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));
    }

    private Permission getOrCreatePermission(String permissionName) {
        return permissionRepository.findByName(permissionName)
                .orElseGet(() -> permissionRepository.save(new Permission(permissionName)));
    }

    private void assignPermission(Role role, Set<Permission> permissions) {
        for (Permission permission : permissions) {
            boolean exists = rolePermissionRepository
                    .existsByRoleIdAndPermissionId(role.getId(), permission.getId());

            if (!exists) {
                RolePermission rp = new RolePermission();
                rp.setRole(role);
                rp.setPermission(permission);
                rolePermissionRepository.save(rp);
            }
        }
    }
}
