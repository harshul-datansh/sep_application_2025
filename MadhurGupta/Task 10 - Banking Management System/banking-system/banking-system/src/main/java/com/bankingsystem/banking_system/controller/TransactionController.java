package com.bankingsystem.banking_system.controller;

import com.bankingsystem.banking_system.dto.transaction.DepositRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionDTO;
import com.bankingsystem.banking_system.dto.transaction.TransactionRequestDTO;
import com.bankingsystem.banking_system.dto.transaction.WithdrawRequestDTO;
import com.bankingsystem.banking_system.service.impl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @Operation(
            summary = "Deposit into account",
            description = "Deposit funds into the authenticated user's account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deposit successful",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"type\": \"DEPOSIT\", \"status\": \"COMPLETED\", \"timestamp\": \"2025-10-12T14:30:00\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid deposit request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("/deposit")
    public ResponseEntity<TransactionDTO> deposit(
            @RequestBody DepositRequestDTO dto,
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(transactionService.deposit(dto, authorizationHeader));
    }

    @Operation(
            summary = "Withdraw from account",
            description = "Withdraw funds from the authenticated user's account.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Withdrawal successful",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 2, \"accountId\": 123, \"amount\": 200.0, \"type\": \"WITHDRAW\", \"status\": \"COMPLETED\", \"timestamp\": \"2025-10-12T15:00:00\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid withdrawal request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDTO> withdraw(
            @RequestBody WithdrawRequestDTO dto,
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(transactionService.withdraw(dto, authorizationHeader));
    }

    @Operation(
            summary = "Transfer funds between accounts",
            description = "Transfer money from one account to another.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Transfer successful",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 3, \"fromAccountId\": 123, \"toAccountId\": 456, \"amount\": 100.0, \"type\": \"TRANSFER\", \"status\": \"COMPLETED\", \"timestamp\": \"2025-10-12T15:30:00\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid transfer request")
            }
    )
    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transfer(@RequestBody TransactionRequestDTO dto) {
        return ResponseEntity.ok(transactionService.transfer(dto));
    }

    @Operation(
            summary = "Get all transactions",
            description = "Retrieve a list of all transactions.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of transactions",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionDTO.class),
                                    examples = @ExampleObject(value = "[{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"type\": \"DEPOSIT\", \"status\": \"COMPLETED\", \"timestamp\": \"2025-10-12T14:30:00\" }]")
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @Operation(
            summary = "Get transaction by ID",
            description = "Retrieve details of a specific transaction by its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Transaction retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TransactionDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"type\": \"DEPOSIT\", \"status\": \"COMPLETED\", \"timestamp\": \"2025-10-12T14:30:00\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Transaction not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactionDTO = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transactionDTO);
    }
}
