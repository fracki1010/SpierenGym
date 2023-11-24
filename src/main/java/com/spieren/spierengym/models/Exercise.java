package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private String img;

    @ElementCollection
    private List<Weekday> weekdays = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @OneToOne(mappedBy = "exercise")
    private Ranking ranking;

    @OneToOne(mappedBy = "exercise")
    private WeightMax weight;


    /*@OneToOne(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Detail detail;*/

    /*@OneToMany(mappedBy = "exercise", fetch = FetchType.EAGER)
    private Set<Detail> details = new HashSet<>();*/

    public Exercise() {
    }

    public Exercise(String name, List<Weekday> weekdays, String description, String instructions,
                    MuscleGroup muscleGroup, String img) {
        this.name = name;
        this.weekdays = weekdays;
        this.description = description;
        this.instructions = instructions;
        this.muscleGroup = muscleGroup;
        this.img = img;
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

    public Routine getRoutine() {
        return routine;
    }

    public void setRoutine(Routine routine) {
        this.routine = routine;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    /*public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }*/

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public List<Weekday> getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(List<Weekday> weekdays) {
        this.weekdays = weekdays;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    public WeightMax getWeight() {
        return weight;
    }

    public void setWeight(WeightMax weight) {
        this.weight = weight;
    }

    /*public void addDetail(Detail detailCurrent) {
        detailCurrent.setExercise(this);
        this.detail = detailCurrent;
    }*/

}
