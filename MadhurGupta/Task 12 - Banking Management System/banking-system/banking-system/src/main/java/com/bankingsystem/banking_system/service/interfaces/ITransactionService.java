package com.bankingsystem.banking_system.service.interfaces;

import com.bankingsystem.banking_system.dto.transaction.DepositRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.WithdrawRequestDTO;

import java.util.List;

public interface ITransactionService {

    // Deposit money into the current user's account
    TransactionDTO deposit(DepositRequestDTO request, String authorizationHeader);

    // Withdraw money from the current user's account
    TransactionDTO withdraw(WithdrawRequestDTO request, String authorizationHeader);

    // Transfer money from one account to another (requires source & target IDs)
    TransactionDTO transfer(TransactionRequestDTO request);

    // Get all transactions
    List<TransactionDTO> getAllTransactions();

    // Get transaction by ID
    TransactionDTO getTransactionById(Long id);
}
