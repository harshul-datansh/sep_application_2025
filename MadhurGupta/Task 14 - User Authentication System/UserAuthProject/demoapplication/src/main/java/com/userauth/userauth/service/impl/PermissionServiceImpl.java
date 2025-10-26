package com.userauth.userauth.service.impl;

import com.userauth.userauth.entity.Permission;
import com.userauth.userauth.repository.PermissionRepository;
import com.userauth.userauth.service.ServiceInterface.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existing = permissionRepository.findById(id).orElseThrow();
        existing.setName(permission.getName());
        return permissionRepository.save(existing);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
}
