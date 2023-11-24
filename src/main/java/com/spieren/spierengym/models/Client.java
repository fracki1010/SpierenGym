package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String phone;
    private Gender gender;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate startDate;
    private String shift;
    private RolType rol;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Routine> routines = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "client_ranking", // Nombre de la tabla de relación
            joinColumns = @JoinColumn(name = "client_id"), // Columna que hace referencia a Client
            inverseJoinColumns = @JoinColumn(name = "ranking_id") // Columna que hace referencia a Ranking
    )
    private Set<Ranking> rankings = new HashSet<>();

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private Set<WeightMax> weightMaxs = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName, String dni, String phone, Gender gender, String email, String password, LocalDate birthDate, LocalDate startDate, String shift, RolType rol) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.shift = shift;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public RolType getRol() {
        return rol;
    }

    public void setRol(RolType rol) {
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(Set<Ranking> rankings) {
        this.rankings = rankings;
    }

    public Set<WeightMax> getWeightMaxs() {
        return weightMaxs;
    }

    public void setWeightMaxs(Set<WeightMax> weightMaxs) {
        this.weightMaxs = weightMaxs;
    }

    public void addPayment(Payment payment){
        //Decirle a la payment quien es su dueño
        payment.setClient(this);
        payments.add(payment);
    }

    public void removePayment(Payment payment){
        payments.remove(payment);
    }

    public void addRoutine(Routine routine){
        routine.setClient(this);
        routines.add(routine);
    }

    public void removeRoutine(Routine routine){
        routines.remove(routine);
    }


    public void addRankingAndClient(Ranking ranking) {
        rankings.add(ranking);
        ranking.getClients().add(this);
    }

}
