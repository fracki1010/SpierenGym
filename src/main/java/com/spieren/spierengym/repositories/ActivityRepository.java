package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
