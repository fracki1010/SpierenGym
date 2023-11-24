package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WeightMax {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private int weight;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name="exercise_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public WeightMax() {
    }

    public WeightMax(int weight, Exercise exercise, Client client, LocalDate date) {
        this.weight = weight;
        this.date = date;
        this.exercise = exercise;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}
