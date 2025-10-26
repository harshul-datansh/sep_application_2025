-- ===============================
-- Create roles table
-- ===============================
CREATE TABLE IF NOT EXISTS roles (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     name VARCHAR(50) NOT NULL UNIQUE
);

-- ===============================
-- Create permissions table
-- ===============================
CREATE TABLE IF NOT EXISTS permissions (
                                           id INT PRIMARY KEY AUTO_INCREMENT,
                                           name VARCHAR(50) NOT NULL UNIQUE
);

-- ===============================
-- Create role_permission mapping table
-- ===============================
CREATE TABLE IF NOT EXISTS role_permission (
                                               role_id INT NOT NULL,
                                               permission_id INT NOT NULL,
                                               PRIMARY KEY (role_id, permission_id),
                                               CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
                                               CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

-- ===============================
-- Insert roles (if not exists)
-- ===============================
INSERT INTO roles (name)
SELECT 'admin' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'admin');

INSERT INTO roles (name)
SELECT 'editor' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'editor');

INSERT INTO roles (name)
SELECT 'viewer' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'viewer');

-- ✅ Fix: Add ROLE_USER to support default role
INSERT INTO roles (name)
SELECT 'ROLE_USER' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ROLE_USER');

-- ===============================
-- Insert permissions (if not exists)
-- ===============================
INSERT INTO permissions (name)
SELECT 'create_post' WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'create_post');

INSERT INTO permissions (name)
SELECT 'edit_post' WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'edit_post');

INSERT INTO permissions (name)
SELECT 'delete_post' WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'delete_post');

INSERT INTO permissions (name)
SELECT 'view_post' WHERE NOT EXISTS (SELECT 1 FROM permissions WHERE name = 'view_post');

-- ===============================
-- Role-permission mappings
-- ===============================

-- Admin gets ALL permissions
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE r.name = 'admin'
  AND NOT EXISTS (
    SELECT 1 FROM role_permission rp WHERE rp.role_id = r.id AND rp.permission_id = p.id
);

-- Editor gets create_post, edit_post, view_post
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE r.name = 'editor'
  AND p.name IN ('create_post', 'edit_post', 'view_post')
  AND NOT EXISTS (
    SELECT 1 FROM role_permission rp WHERE rp.role_id = r.id AND rp.permission_id = p.id
);

-- Viewer gets view_post only
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE r.name = 'viewer'
  AND p.name = 'view_post'
  AND NOT EXISTS (
    SELECT 1 FROM role_permission rp WHERE rp.role_id = r.id AND rp.permission_id = p.id
);

-- (Optional) ROLE_USER: assign only view_post like 'viewer'
INSERT INTO role_permission (role_id, permission_id)
SELECT r.id, p.id
FROM roles r, permissions p
WHERE r.name = 'ROLE_USER'
  AND p.name = 'view_post'
  AND NOT EXISTS (
    SELECT 1 FROM role_permission rp WHERE rp.role_id = r.id AND rp.permission_id = p.id
);
