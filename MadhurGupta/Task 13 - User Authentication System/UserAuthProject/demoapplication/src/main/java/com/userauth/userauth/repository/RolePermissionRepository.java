package com.userauth.userauth.repository;

import com.userauth.userauth.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleName(String roleName);
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);

}
