package com.bankingsystem.banking_system.entity;

import com.bankingsystem.banking_system.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(nullable = false, length = 255)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // EMAIL, IN_APP

    @Column(name = "read_flag", nullable = false)
    private Boolean readFlag = false;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Optional: link to transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Transaction relatedTransaction;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
