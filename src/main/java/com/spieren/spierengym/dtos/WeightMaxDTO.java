package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.WeightMax;

import java.time.LocalDate;

public class WeightMaxDTO {

    private Long id;
    private int weight;
    private ExerciseDTO exercise;
    private ClientDTO client;
    private LocalDate date;

    public WeightMaxDTO(WeightMax weightMax) {
        this.id = weightMax.getId();
        this.weight = weightMax.getWeight();
        this.date = weightMax.getDate();
        this.exercise = new ExerciseDTO(weightMax.getExercise());
    }

    public Long getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public ExerciseDTO getExercise() {
        return exercise;
    }

    public ClientDTO getClient() {
        return client;
    }

    public LocalDate getDate() {
        return date;
    }
}
