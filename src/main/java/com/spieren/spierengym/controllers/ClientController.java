package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ClientDTO;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClient() {
        List<ClientDTO> allClients = clientRepository
                                        .findAll()
                                        .stream()
                                        .map(client -> new ClientDTO(client))
                                        .collect(Collectors.toList());
        return allClients;
    }
}
