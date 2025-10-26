package com.bankingsystem.banking_system.service.impl;

import com.bankingsystem.banking_system.dto.account.AccountRequestDTO;
import com.bankingsystem.banking_system.entity.Account;
import com.bankingsystem.banking_system.entity.User;
import com.bankingsystem.banking_system.enums.AccountStatus;
import com.bankingsystem.banking_system.enums.AccountType;
import com.bankingsystem.banking_system.exception.ValidationException;
import com.bankingsystem.banking_system.repository.AccountRepository;
import com.bankingsystem.banking_system.repository.UserRepository;
import com.bankingsystem.banking_system.service.interfaces.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    /** Create a new account for a user */
    @Override
    public Account createAccount(AccountRequestDTO dto) {
        // Find user by ID
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ValidationException("User not found with id: " + dto.getUserId()));

        // Map DTO to entity
        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber()); // generate automatically
        account.setAccountType(AccountType.valueOf(dto.getAccountType().toUpperCase())); // convert string to enum
        account.setBalance(dto.getBalance() != null ? dto.getBalance() : BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE); // default active

        return accountRepository.save(account);
    }

    /** Get all accounts */
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /** Get account by ID */
    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Account not found with id: " + id));
    }

    /** Get account by account number */
    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ValidationException("Account not found with account number: " + accountNumber));
    }

    /** Update account status (ACTIVE / FROZEN) */
    @Override
    public Account updateAccountStatus(Long id, AccountStatus status) {
        Account account = getAccountById(id);
        account.setStatus(status);
        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf((long) (Math.random() * 1_000_000_000_000L)); // 12-digit number
        } while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

}
