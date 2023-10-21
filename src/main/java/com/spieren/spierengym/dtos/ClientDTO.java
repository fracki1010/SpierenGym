package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Activity;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Payment;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate date;
    private LocalDate birthDate;
    private LocalDate startDate;
    private Set<PaymentDTO> payments = new HashSet<>();
    private Set<ActivityDTO> activities = new HashSet<>();

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.address = client.getAddress();
        this.date = client.getDate();
        this.birthDate = client.getBirthDate();
        this.startDate = client.getStartDate();
        this.payments = client.getPayments()
                                .stream()
                                .map(payment -> new PaymentDTO(payment))
                                .collect(Collectors.toSet());
        this.activities = client.getActivities()
                                .stream()
                                .map(activity -> new ActivityDTO(activity))
                                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Set<PaymentDTO> getPayments() {
        return payments;
    }

    public Set<ActivityDTO> getActivities() {
        return activities;
    }
}
