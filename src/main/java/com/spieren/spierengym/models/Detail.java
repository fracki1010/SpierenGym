package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private int repeat;
    private int weight;
    private int series;


    /*@OneToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;*/

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;*/

    @OneToMany(mappedBy = "detail", fetch = FetchType.EAGER)
    private Set<Exercise> exercises = new HashSet<>();

    public Detail() {
    }

    public Detail(int repeat, int weight, int series) {
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

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        exercise.setDetail(this);
        this.exercises.add(exercise);
    }
}
