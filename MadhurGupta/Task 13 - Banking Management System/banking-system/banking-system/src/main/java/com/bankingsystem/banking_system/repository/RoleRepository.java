package com.bankingsystem.banking_system.repository;


import com.bankingsystem.banking_system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(String roleName);

}
