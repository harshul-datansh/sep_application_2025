-- Update admin password to correct BCrypt hash for 'admin123'
UPDATE users
SET password = '$2a$10$J0alqPU73PftUMQHLX9fveluaAiqcZqNoabxVNa52N4Gf6phRidii'
WHERE email = 'admin@bank.com';
