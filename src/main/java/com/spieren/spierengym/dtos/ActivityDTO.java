package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Activity;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Routine;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ActivityDTO {
    private Long id;
    private String timetable;
    private Routine routine;

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.timetable = activity.getTimetable();
        this. routine = activity.getRoutine();
    }

    public Long getId() {
        return id;
    }

    public String getTimetable() {
        return timetable;
    }

    public Routine getRoutine() {
        return routine;
    }
}
