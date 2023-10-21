package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Activity;
import com.spieren.spierengym.models.DetailRoutine;
import com.spieren.spierengym.models.Dificulty;
import com.spieren.spierengym.models.Routine;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RoutineDTO {
    private Long id;
    private String name;
    private String description;
    private String creator;
    private String objective;
    private Dificulty dificulty;
    private Set<DetailRoutine> detailRoutines = new HashSet<>();

    public RoutineDTO(Routine routine) {
        this.id = routine.getId();
        this.name = routine.getName();
        this.description = routine.getDescription();
        this.creator = routine.getCreator();
        this.objective = routine.getObjective();
        this.dificulty = routine.getDificulty();
        /*this.activities = routine.getActivities()
                                    .stream()
                                    .map(activity -> new ActivityDTO(activity))
                                    .collect(Collectors.toSet());*/
        this.detailRoutines = routine.getDetailRoutines();
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

    public Dificulty getDificulty() {
        return dificulty;
    }

    public Set<DetailRoutine> getDetailRoutines() {
        return detailRoutines;
    }
}
