package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Payment;
import com.spieren.spierengym.models.RolType;
import com.spieren.spierengym.models.Routine;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String password;
    private String address;
    private LocalDate date;
    private LocalDate birthDate;
    private LocalDate startDate;
    private String shift;
    private RolType rol;
    private Set<PaymentDTO> payments = new HashSet<>();
    private Set<RoutineDTO> routines;


    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.dni = client.getDni();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.birthDate = client.getBirthDate();
        this.startDate = client.getStartDate();
        this.shift = client.getShift();
        this.rol = client.getRol();
        this.payments = client.getPayments()
                                .stream()
                                .map(payment -> new PaymentDTO(payment))
                                .collect(Collectors.toSet());
        this.routines = client.getRoutines()
                              .stream()
                              .map(routine -> new RoutineDTO(routine))
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getPassword() {
        return password;
    }

    public String getShift() {
        return shift;
    }

    public String getDni() {
        return dni;
    }

    public RolType getRol() {
        return rol;
    }

    public Set<PaymentDTO> getPayments() {
        return payments;
    }

    public Set<RoutineDTO> getRoutines() {
        return routines;
    }
}
