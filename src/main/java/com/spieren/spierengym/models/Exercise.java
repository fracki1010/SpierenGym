package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private MuscleGroup muscleGroup ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detailRoutine_id")
    private DetailRoutine detailRoutine;

    public Exercise(String name, String description, String instructions, MuscleGroup muscleGroup) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.muscleGroup = muscleGroup;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }
}
