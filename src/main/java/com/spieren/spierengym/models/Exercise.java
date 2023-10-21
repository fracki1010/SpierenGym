package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private MuscleGroup muscleGroup ;

    @OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER)
    private Set<DetailRoutine> detailRoutines = new HashSet<>();


    public Exercise() {
    }

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

    public Set<DetailRoutine> getDetailRoutines() {
        return detailRoutines;
    }

    public void setDetailRoutines(Set<DetailRoutine> detailRoutines) {
        this.detailRoutines = detailRoutines;
    }

    public void addDetailRoutine(DetailRoutine detailRoutine) {
        detailRoutine.setExercise(this);
        detailRoutines.add(detailRoutine);
    }
}
