package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Payment;
import com.spieren.spierengym.models.PaymentMethod;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.Locale;

public class PaymentDTO {
    private Long id;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private boolean paymentStatus;
    private LocalDate paymentDue;
    private String month;
    private Double amount;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.paymentMethod = payment.getPaymentMethod();
        this.paymentDate = payment.getPaymentDate();
        this.paymentStatus = payment.isPaymentStatus();
        this.paymentDue = payment.getPaymentDue();
        this.month = payment.getPaymentDue().minusMonths(1)
                /*Obtener mes que se debe pagar */.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
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

    public LocalDate getPaymentDue() {
        return paymentDue;
    }

    public String getMonth() {
        return month;
    }

    public Double getAmount() {
        return amount;
    }

}
