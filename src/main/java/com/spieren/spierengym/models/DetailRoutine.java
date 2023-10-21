package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class DetailRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private int repeat;
    private int weight;
    private int series;
    @OneToMany(mappedBy = "detailRoutine", fetch = FetchType.EAGER)
    private Set<Routine> routines = new HashSet<>();

    @OneToMany(mappedBy = "detailRountine", fetch = FetchType.EAGER)
    private Set<Exercise> exercises = new HashSet<>();

    public DetailRoutine(int repeat, int weight, int series) {
        this.repeat = repeat;
        this.weight = weight;
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}
