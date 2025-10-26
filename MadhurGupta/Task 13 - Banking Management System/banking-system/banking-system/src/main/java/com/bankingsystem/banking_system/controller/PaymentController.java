package com.bankingsystem.banking_system.controller;

import com.bankingsystem.banking_system.dto.payment.PaymentDTO;
import com.bankingsystem.banking_system.service.impl.PaymentServiceImpl;
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
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Operation(
            summary = "Create a new payment",
            description = "Creates a new payment transaction with the provided details.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Payment created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"status\": \"PENDING\", \"type\": \"CREDIT\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid payment data")
            }
    )
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.createPayment(paymentDTO));
    }

    @Operation(
            summary = "Get all payments",
            description = "Retrieves a list of all payment transactions.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of payments",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentDTO.class),
                                    examples = @ExampleObject(value = "[{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"status\": \"PENDING\", \"type\": \"CREDIT\" }]")
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @Operation(
            summary = "Get payment by ID",
            description = "Fetches a specific payment transaction by its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Payment retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"status\": \"PENDING\", \"type\": \"CREDIT\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @Operation(
            summary = "Update payment status",
            description = "Updates the status of a specific payment (e.g., PENDING, COMPLETED, FAILED).",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Payment status updated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PaymentDTO.class),
                                    examples = @ExampleObject(value = "{ \"id\": 1, \"accountId\": 123, \"amount\": 500.0, \"status\": \"COMPLETED\", \"type\": \"CREDIT\" }")
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            }
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentDTO> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(paymentService.updatePaymentStatus(id, status));
    }
}
