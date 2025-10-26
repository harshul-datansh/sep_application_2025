-- ============================================
-- V2__seed_data.sql
-- Insert base roles and admin user (H2)
-- ============================================

-- Insert Roles
INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('CUSTOMER');

-- Insert Admin User
-- Password is BCrypt hash for 'admin123'
INSERT INTO users (name, email, password)
VALUES ('System Admin', 'admin@bank.com', '$2a$10$mtNxKHlHCra1RZycoHnavOe5xPkHV1hbN4Jr7lzqqQsBYIsU8cudG');

-- Assign ADMIN Role to Admin User
INSERT INTO user_roles (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE email = 'admin@bank.com' LIMIT 1),
       (SELECT id FROM roles WHERE name = 'ADMIN' LIMIT 1)
    );
