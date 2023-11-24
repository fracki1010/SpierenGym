package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.WeightMax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WeightMaxRepository extends JpaRepository<WeightMax, Long> {
}
