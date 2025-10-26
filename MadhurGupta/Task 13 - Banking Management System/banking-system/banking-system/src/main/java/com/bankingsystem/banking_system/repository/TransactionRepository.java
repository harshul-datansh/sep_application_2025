package com.bankingsystem.banking_system.repository;

import com.bankingsystem.banking_system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {
}
