package com.bankingsystem.banking_system.controller;

import com.bankingsystem.banking_system.dto.account.AccountDTO;
import com.bankingsystem.banking_system.dto.account.AccountRequestDTO;
import com.bankingsystem.banking_system.entity.Account;
import com.bankingsystem.banking_system.enums.AccountStatus;
import com.bankingsystem.banking_system.mapper.AccountMapper;
import com.bankingsystem.banking_system.service.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;

    @Operation(
            summary = "Create a new account",
            description = "Creates a new bank account with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountNumber\": \"1234567890\", \"balance\": 1000, \"status\": \"ACTIVE\", \"type\": \"SAVINGS\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid account request")
            }
    )
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountRequestDTO dto) {
        Account created = accountService.createAccount(dto);
        return ResponseEntity.ok(AccountMapper.toDTO(created));
    }

    @Operation(
            summary = "Get all accounts",
            description = "Retrieves a list of all bank accounts.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of accounts",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDTO.class),
                                    examples = @ExampleObject(value = "[{ \"id\": 1, \"accountNumber\": \"1234567890\", \"balance\": 1000, \"status\": \"ACTIVE\", \"type\": \"SAVINGS\" }]")
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accounts = accountService.getAllAccounts()
                .stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }

    @Operation(
            summary = "Get account by ID",
            description = "Fetches account details by account ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountNumber\": \"1234567890\", \"balance\": 1000, \"status\": \"ACTIVE\", \"type\": \"SAVINGS\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Account not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(AccountMapper.toDTO(account));
    }

    @Operation(
            summary = "Update account status",
            description = "Updates the status of an account (e.g., ACTIVE, INACTIVE).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Account status updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AccountDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountNumber\": \"1234567890\", \"balance\": 1000, \"status\": \"INACTIVE\", \"type\": \"SAVINGS\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Account not found")
            }
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<AccountDTO> updateAccountStatus(
            @PathVariable Long id,
            @RequestParam AccountStatus status) {
        Account updated = accountService.updateAccountStatus(id, status);
        return ResponseEntity.ok(AccountMapper.toDTO(updated));
    }
}
