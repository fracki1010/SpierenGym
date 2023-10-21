package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.DetailRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DetailRoutineRepository extends JpaRepository<DetailRoutine, Long> {

}
