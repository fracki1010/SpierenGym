package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Boolean existsByName(String name);
}
