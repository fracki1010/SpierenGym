package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Routine;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoutineDTO {
    private Long id;
    private String name;
    private String description;
    private String creator;
    private String objective;
    private Set<ExerciseDTO> exercises = new HashSet<>();

    public RoutineDTO(Routine routine) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.description = routine.getDescription();
        this.creator = routine.getCreator();
        this.objective = routine.getObjective();
        this.exercises = routine.getExercises()
                                    .stream()
                                    .map(exercise -> new ExerciseDTO(exercise))
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

    public String getCreator() {
        return creator;
    }

    public String getObjective() {
        return objective;
    }

    public Set<ExerciseDTO> getExercises() {
        return exercises;
    }

}
