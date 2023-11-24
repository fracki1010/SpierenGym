package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Exercise;
import com.spieren.spierengym.models.MuscleGroup;
import com.spieren.spierengym.models.Weekday;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private MuscleGroup muscleGroup ;
    private List<Weekday> weekdays = new ArrayList<>();
    private String img;
    private DetailDTO details;

    public ExerciseDTO(Exercise exercise) {
        this.id = exercise.getId();
        this.description = exercise.getDescription();
        this.name = exercise.getName();
        this.instructions = exercise.getInstructions();
        this.muscleGroup = exercise.getMuscleGroup();
        this.weekdays = exercise.getWeekdays();
        this.img = exercise.getImg();
        this.details = new DetailDTO(exercise.getDetail());
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

    public List<Weekday> getWeekdays() {
        return weekdays;
    }

    public String getImg() {
        return img;
    }
    /*public Set<DetailDTO> getDetails() {
        return details;
    }*/

    public DetailDTO getDetails() {
        return details;
    }
}
