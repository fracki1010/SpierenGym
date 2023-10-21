package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "routine")
    private Set<Activity> activities = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detailRoutine_id")
    private DetailRoutine detailRoutine;

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
}
