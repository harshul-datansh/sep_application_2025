package com.bankingsystem.banking_system.service.interfaces;

import com.bankingsystem.banking_system.dto.payment.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(Long id);
    PaymentDTO updatePaymentStatus(Long id, String status);
}
