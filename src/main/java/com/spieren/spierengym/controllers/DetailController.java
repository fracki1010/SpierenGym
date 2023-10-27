package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.DetailDTO;
import com.spieren.spierengym.repositories.DetailRoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DetailController {

    @Autowired
    DetailRoutineRepository detailRoutineRepository;

    @GetMapping("/details")
    public Set<DetailDTO> allDetails (){
        return detailRoutineRepository.findAll()
                                      .stream()
                                      .map(detail -> new DetailDTO(detail))
                                      .collect(Collectors.toSet());
    }

}
