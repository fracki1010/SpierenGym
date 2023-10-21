package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoutineRepository extends JpaRepository<Routine, Long> {

}
