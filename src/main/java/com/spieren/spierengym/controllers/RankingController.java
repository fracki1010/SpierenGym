package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.RankingDTO;
import com.spieren.spierengym.models.Ranking;
import com.spieren.spierengym.repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RankingController {

    @Autowired
    RankingRepository rankingRepository;

    @GetMapping("/rankings")
    public Set<RankingDTO> allRanking(){
        return rankingRepository.findAll()
                                .stream()
                                .map(ranking -> new RankingDTO(ranking))
                                .collect(Collectors.toSet());
    }

    @GetMapping("/rankings/{id}")
    public RankingDTO getRankingById(@PathVariable Long id) {
        Ranking ranking = rankingRepository.findById(id).orElse(null);
        return new RankingDTO(ranking);
    }
}
