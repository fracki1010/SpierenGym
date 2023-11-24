package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private LocalDate paymentDue;
    private boolean paymentStatus;
    private Double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Payment() {
    }

    public Payment(LocalDate paymentDue , boolean paymentStatus, Double amount) {
        this.paymentStatus = paymentStatus;
        this.paymentDue = paymentDue;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(LocalDate paymentDue) {
        this.paymentDue = paymentDue;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
