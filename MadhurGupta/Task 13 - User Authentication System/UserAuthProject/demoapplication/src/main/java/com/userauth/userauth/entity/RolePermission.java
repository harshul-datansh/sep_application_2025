package com.userauth.userauth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"role", "permission"})  // Composite identity
@Table(
        name = "role_permission",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})}
)
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    // Convenience constructor
    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }
}
