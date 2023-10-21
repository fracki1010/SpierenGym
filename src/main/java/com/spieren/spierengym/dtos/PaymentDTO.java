package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Payment;
import com.spieren.spierengym.models.PaymentMethod;

import java.time.LocalDate;

public class PaymentDTO {
    private Long id;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private boolean paymentStatus;
    private Double amount;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.paymentMethod = payment.getPaymentMethod();
        this.paymentDate = payment.getPaymentDate();
        this.paymentStatus = payment.isPaymentStatus();
        this.amount = payment.getAmount();
    }

    public Long getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

}
