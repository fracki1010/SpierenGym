package com.spieren.spierengym.models;

import com.spieren.spierengym.dtos.RoutineDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private String name;
    private String description;
    private String creator;
    private String objective;
    private Dificulty dificulty;


    @OneToMany(mappedBy = "routine", fetch = FetchType.EAGER)
    private Set<Exercise> exercises = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    public Routine() {
    }

    public Routine(String name, String description, String creator, String objective, Dificulty dificulty) {
        this.name = name;
        this.description = description;
        this.creator = creator;
        this.objective = objective;
        this.dificulty = dificulty;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Dificulty getDificulty() {
        return dificulty;
    }

    public void setDificulty(Dificulty dificulty) {
        this.dificulty = dificulty;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addExercise(Exercise exercise) {
        exercise.setRoutine(this);
        exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise) {
        exercise.setRoutine(null);
        exercises.remove(exercise);
    }
}
