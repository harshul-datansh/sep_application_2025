-- ============================================
-- V1__init_schema.sql
-- Initial schema for Banking Management System (H2)
-- ============================================

-- USERS TABLE
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
);

-- ROLES TABLE
CREATE TABLE roles (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL UNIQUE
);

-- USER_ROLES TABLE
CREATE TABLE user_roles (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            CONSTRAINT fk_userrole_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                            CONSTRAINT fk_userrole_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- ACCOUNTS TABLE
CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT NOT NULL,
                          account_number VARCHAR(20) NOT NULL UNIQUE,
                          account_type VARCHAR(20) NOT NULL,
                          balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
                          status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                          CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- TRANSACTIONS TABLE
CREATE TABLE transactions (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              source_account_id BIGINT NULL,
                              target_account_id BIGINT NULL,
                              amount DECIMAL(15,2) NOT NULL,
                              type VARCHAR(20) NOT NULL,
                              status VARCHAR(20) NOT NULL,
                              description VARCHAR(255),
                              timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                              CONSTRAINT fk_transaction_source FOREIGN KEY (source_account_id) REFERENCES accounts(id),
                              CONSTRAINT fk_transaction_target FOREIGN KEY (target_account_id) REFERENCES accounts(id)
);

-- PAYMENTS TABLE
CREATE TABLE payments (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          account_id BIGINT NOT NULL,
                          amount DECIMAL(15,2) NOT NULL,
                          gateway_transaction_id VARCHAR(100) UNIQUE,
                          status VARCHAR(20) NOT NULL,
                          payment_type VARCHAR(30) NOT NULL,
                          description VARCHAR(255),
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                          CONSTRAINT fk_payment_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
);

-- NOTIFICATIONS TABLE
CREATE TABLE notifications (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               message VARCHAR(255) NOT NULL,
                               type VARCHAR(20) NOT NULL,
                               read_flag BOOLEAN NOT NULL DEFAULT FALSE,
                               timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                               transaction_id BIGINT NULL,
                               CONSTRAINT fk_notification_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               CONSTRAINT fk_notification_txn FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

-- REFRESH TOKENS TABLE
CREATE TABLE refresh_tokens (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                token VARCHAR(255) NOT NULL UNIQUE,
                                expiry_date TIMESTAMP NOT NULL,
                                revoked BOOLEAN NOT NULL DEFAULT FALSE,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
                                CONSTRAINT fk_refreshtoken_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Add missing 'status' column to accounts table (for AccountStatus enum)
ALTER TABLE accounts ADD COLUMN IF NOT EXISTS status VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL;

-- Add missing 'status' column to refresh_tokens table
ALTER TABLE refresh_tokens ADD COLUMN IF NOT EXISTS status VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL;

