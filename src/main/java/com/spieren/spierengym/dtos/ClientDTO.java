package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phone;
    private Gender gender;
    private LocalDate birthDate;
    private int age;
    private LocalDate startDate;
    private String shift;
    private RolType rol;
    private Set<PaymentDTO> payments = new HashSet<>();
    private Set<RoutineDTO> routines = new HashSet<>();
    private Set<WeightMaxDTO> weightMaxs = new HashSet<>();


    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.dni = client.getDni();
        this.phone = client.getPhone();
        this.gender = client.getGender();
        this.email = client.getEmail();
        this.birthDate = client.getBirthDate();
        this.age = Period.between(client.getBirthDate(), LocalDate.now()).getYears();
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
        this.weightMaxs = client.getWeightMaxs()
                                .stream()
                                .map(weightMax -> new WeightMaxDTO(weightMax))
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

    public String getShift() {
        return shift;
    }

    public String getDni() {
        return dni;
    }

    public RolType getRol() {
        return rol;
    }

    public String getPhone() {
        return phone;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Set<PaymentDTO> getPayments() {
        return payments;
    }

    public Set<RoutineDTO> getRoutines() {
        return routines;
    }

    public Set<WeightMaxDTO> getWeightMaxs() {
        return weightMaxs;
    }
}
