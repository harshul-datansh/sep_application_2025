package com.bankingsystem.banking_system.service.interfaces;

import com.bankingsystem.banking_system.dto.account.AccountRequestDTO;
import com.bankingsystem.banking_system.entity.Account;
import com.bankingsystem.banking_system.enums.AccountStatus;

import java.util.List;

public interface IAccountService {

    Account createAccount(AccountRequestDTO accountRequestDTO);

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    Account getAccountByNumber(String accountNumber);

    Account updateAccountStatus(Long id, AccountStatus status);
}
