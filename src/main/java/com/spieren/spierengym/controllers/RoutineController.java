package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.RoutineDTO;
import com.spieren.spierengym.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RoutineController {

    @Autowired
    RoutineRepository routineRepository;

    @GetMapping("/routines")
    public Set<RoutineDTO> getAllRoutines() {
        Set<RoutineDTO> allRoutine = routineRepository.findAll()
                                                      .stream()
                                                      .map(routine -> new RoutineDTO(routine))
                                                      .collect(Collectors.toSet());
        return allRoutine;
    }

}
