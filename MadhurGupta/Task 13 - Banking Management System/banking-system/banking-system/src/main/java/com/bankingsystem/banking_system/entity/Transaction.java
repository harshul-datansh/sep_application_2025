package com.bankingsystem.banking_system.entity;

import com.bankingsystem.banking_system.enums.TransactionStatus;
import com.bankingsystem.banking_system.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Account from which funds are deducted (nullable for deposit)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account sourceAccount;

    // Account receiving funds (nullable for withdrawal)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Account targetAccount;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type; // TRANSFER, WITHDRAWAL, DEPOSIT

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status; // PENDING, COMPLETED, FAILED

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
