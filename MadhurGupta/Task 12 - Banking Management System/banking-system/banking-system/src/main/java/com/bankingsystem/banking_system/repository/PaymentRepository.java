package com.bankingsystem.banking_system.repository;

import com.bankingsystem.banking_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
