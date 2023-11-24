package com.spieren.spierengym.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;
    private String name;
    private String image;
    @ManyToMany(mappedBy = "rankings")
    private Set<Client> clients = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Ranking() {
    }

    public Ranking(String name, Exercise exercise, String image) {
        this.name = name;
        this.image = image;
        this.exercise = exercise;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }



}
