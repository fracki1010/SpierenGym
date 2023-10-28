package com.spieren.spierengym.repositories;

import com.spieren.spierengym.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Boolean existsByDni(String dni);

    Boolean existsByPhone(String phone);
}
