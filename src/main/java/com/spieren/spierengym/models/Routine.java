package com.spieren.spierengym.models;

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

    @OneToMany(mappedBy = "routine")
    private Set<Activity> activities = new HashSet<>();

    @OneToMany(mappedBy = "routine", fetch = FetchType.EAGER)
    private Set<DetailRoutine> detailRoutines = new HashSet<>();

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

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<DetailRoutine> getDetailRoutines() {
        return detailRoutines;
    }

    public void setDetailRoutines(Set<DetailRoutine> detailRoutines) {
        this.detailRoutines = detailRoutines;
    }

    public void addActivity(Activity activity) {
        activity.setRoutine(this);
        activities.add(activity);
    }

    public void addDetailRoutine(DetailRoutine detailRoutine) {
        detailRoutine.setRoutine(this);
        detailRoutines.add(detailRoutine);
    }
}
