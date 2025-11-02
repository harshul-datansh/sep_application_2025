package com.bankingsystem.banking_system.service.impl;

import com.bankingsystem.banking_system.dto.payment.PaymentDTO;
import com.bankingsystem.banking_system.entity.Account;
import com.bankingsystem.banking_system.entity.Payment;
import com.bankingsystem.banking_system.enums.PaymentStatus;
import com.bankingsystem.banking_system.repository.AccountRepository;
import com.bankingsystem.banking_system.repository.PaymentRepository;
import com.bankingsystem.banking_system.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Account account = accountRepository.findById(paymentDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Payment payment = Payment.builder()
                .account(account)
                .amount(paymentDTO.getAmount())
                .description(paymentDTO.getDescription())
                .paymentType(paymentDTO.getPaymentType())
                .status(PaymentStatus.PENDING)
                // Mock payment gateway ID
                .gatewayTransactionId("GW-" + UUID.randomUUID().toString())
                .build();

        // Simulate payment processing
        if (payment.getAmount() > 0) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }

        Payment savedPayment = paymentRepository.save(payment);

        return mapToDTO(savedPayment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return mapToDTO(payment);
    }

    @Override
    public PaymentDTO updatePaymentStatus(Long id, String status) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.valueOf(status.toUpperCase()));
        return mapToDTO(paymentRepository.save(payment));
    }

    private PaymentDTO mapToDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .accountId(payment.getAccount().getId())
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .paymentType(payment.getPaymentType())
                .status(payment.getStatus())
                .gatewayTransactionId(payment.getGatewayTransactionId())
                .build();
    }
}
