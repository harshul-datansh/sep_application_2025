package com.bankingsystem.banking_system.service.impl;

import com.bankingsystem.banking_system.dto.notification.NotificationDTO;
import com.bankingsystem.banking_system.dto.transaction.DepositRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.WithdrawRequestDTO;
import com.bankingsystem.banking_system.entity.Account;
import com.bankingsystem.banking_system.entity.Transaction;
import com.bankingsystem.banking_system.enums.NotificationType;
import com.bankingsystem.banking_system.enums.TransactionStatus;
import com.bankingsystem.banking_system.enums.TransactionType;
import com.bankingsystem.banking_system.repository.AccountRepository;
import com.bankingsystem.banking_system.repository.TransactionRepository;
import com.bankingsystem.banking_system.service.interfaces.ITransactionService;
import com.bankingsystem.banking_system.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final NotificationServiceImpl notificationService;
    private final JwtUtil jwtUtil;

    /**
     * Utility: Get the logged-in user's account
     * Assumes each user has exactly one account
     */
    private Account getCurrentUserAccount(String authorizationHeader) {
        Long userId = jwtUtil.getUserIdFromAuthorizationHeader(authorizationHeader);
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user"));
    }

    /** Deposit into current user's account */
    @Override
    @Transactional
    public TransactionDTO deposit(DepositRequestDTO request, String authorizationHeader) {
        Account account = getCurrentUserAccount(authorizationHeader);

        account.setBalance(account.getBalance().add(BigDecimal.valueOf(request.getAmount())));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .targetAccount(account)
                .amount(request.getAmount())
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.COMPLETED)
                .description(request.getDescription())
                .build();

        Transaction saved = transactionRepository.save(transaction);
        notifyTransaction(saved, TransactionType.DEPOSIT);
        return mapToDTO(saved);
    }

    /** Withdraw from current user's account */
    @Override
    @Transactional
    public TransactionDTO withdraw(WithdrawRequestDTO request, String authorizationHeader) {
        Account account = getCurrentUserAccount(authorizationHeader);

        if (account.getBalance().doubleValue() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(request.getAmount())));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .sourceAccount(account)
                .amount(request.getAmount())
                .type(TransactionType.WITHDRAWAL)
                .status(TransactionStatus.COMPLETED)
                .description(request.getDescription())
                .build();

        Transaction saved = transactionRepository.save(transaction);
        notifyTransaction(saved, TransactionType.WITHDRAWAL);
        return mapToDTO(saved);
    }

    /** Transfer operation (requires source & target IDs) */
    @Override
    @Transactional
    public TransactionDTO transfer(TransactionRequestDTO request) {
        Account source = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account target = accountRepository.findById(request.getTargetAccountId())
                .orElseThrow(() -> new RuntimeException("Target account not found"));

        if (source.getBalance().doubleValue() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance in source account");
        }

        source.setBalance(source.getBalance().subtract(BigDecimal.valueOf(request.getAmount())));
        target.setBalance(target.getBalance().add(BigDecimal.valueOf(request.getAmount())));

        accountRepository.save(source);
        accountRepository.save(target);

        Transaction transaction = Transaction.builder()
                .sourceAccount(source)
                .targetAccount(target)
                .amount(request.getAmount())
                .type(TransactionType.TRANSFER)
                .status(TransactionStatus.COMPLETED)
                .description(request.getDescription())
                .build();

        Transaction saved = transactionRepository.save(transaction);
        notifyTransaction(saved, TransactionType.TRANSFER);
        return mapToDTO(saved);
    }

    /** Get all transactions */
    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** Get transaction by ID */
    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return mapToDTO(transaction);
    }

    /** Map entity to DTO */
    private TransactionDTO mapToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        dto.setStatus(transaction.getStatus());
        dto.setDescription(transaction.getDescription());
        dto.setSourceAccountId(transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : null);
        dto.setTargetAccountId(transaction.getTargetAccount() != null ? transaction.getTargetAccount().getId() : null);
        dto.setTimestamp(transaction.getTimestamp());
        return dto;
    }

    /** Notify user of transaction */
    private void notifyTransaction(Transaction transaction, TransactionType type) {
        String message = switch (type) {
            case DEPOSIT -> "Deposit of $" + transaction.getAmount() + " successful to account " +
                    transaction.getTargetAccount().getAccountNumber();
            case WITHDRAWAL -> "Withdrawal of $" + transaction.getAmount() + " successful from account " +
                    transaction.getSourceAccount().getAccountNumber();
            case TRANSFER -> "Transfer of $" + transaction.getAmount() + " from account " +
                    transaction.getSourceAccount().getAccountNumber() + " to account " +
                    transaction.getTargetAccount().getAccountNumber() + " completed";
        };

        Long userId = switch (type) {
            case DEPOSIT -> transaction.getTargetAccount().getUser().getId();
            case WITHDRAWAL -> transaction.getSourceAccount().getUser().getId();
            case TRANSFER -> transaction.getSourceAccount().getUser().getId();
        };

        NotificationDTO dto = NotificationDTO.builder()
                .userId(userId)
                .message(message)
                .type(NotificationType.IN_APP)
                .transactionId(transaction.getId())
                .build();

        notificationService.createNotification(dto);
    }
}
