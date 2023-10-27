package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Exercise;
import com.spieren.spierengym.models.MuscleGroup;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private MuscleGroup muscleGroup ;
    private Set<DetailDTO> details = new HashSet<>();

    public ExerciseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.description = exercise.getDescription();
        this.name = exercise.getName();
        this.instructions = exercise.getInstructions();
        this.muscleGroup = exercise.getMuscleGroup();
        this.details = exercise.getDetails()
                                      .stream()
                                      .map(detail -> new DetailDTO(detail))
                                      .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructions() {
        return instructions;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }

    public Set<DetailDTO> getDetails() {
        return details;
    }
}
