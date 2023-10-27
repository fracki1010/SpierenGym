package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ExerciseDTO;
import com.spieren.spierengym.models.Exercise;
import com.spieren.spierengym.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping("/exercises")
    public Set<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                                 .stream()
                                 .map(exercise -> new ExerciseDTO(exercise))
                                 .collect(Collectors.toSet());
    }

}
