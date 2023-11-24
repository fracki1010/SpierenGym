package com.spieren.spierengym.dtos;

import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Ranking;

import java.util.Set;
import java.util.stream.Collectors;

public class RankingDTO {
    private Long id;
    private String name;
    private ExerciseDTO exercise;
    private String image;
    private Set<ClientDTO> clients;

    public RankingDTO(Ranking ranking) {
        this.id = ranking.getId();
        this.name = ranking.getName();
        this.image = ranking.getImage();
        this.exercise = new ExerciseDTO(ranking.getExercise());
        this.clients = ranking.getClients()
                              .stream()
                              .map(client -> new ClientDTO(client))
                              .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public ExerciseDTO getExercise() {
        return exercise;
    }

    public Set<ClientDTO> getClients() {
        return clients;
    }

}
