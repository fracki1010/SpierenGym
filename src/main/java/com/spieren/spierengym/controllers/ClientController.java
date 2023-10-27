package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ClientDTO;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.RolType;
import com.spieren.spierengym.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PostMapping("/clients")
    public ResponseEntity<Object> register(String firstName, String lastName,
                                           String email, String password, RolType rolType,
                                           String dni, String birthdate, String shift
                                            , Authentication authentication){

        if (firstName.isBlank() || lastName.isBlank()||email.isBlank()||password.isBlank() || dni.isBlank() || birthdate.isBlank()){
            return new ResponseEntity<>("Por favor completa todo los datos", HttpStatus.FORBIDDEN);
        }

        if(clientRepository.findByEmail(email) !=null){
            return new ResponseEntity<>("Este nombre de usuario ya fue utilizado", HttpStatus.FORBIDDEN);
        }

        //Verifica que el dni sea unico en la base de datos
        if (clientRepository.existsByDni(dni)){
            return new ResponseEntity<>("Esta persona ya esta registrada", HttpStatus.FORBIDDEN);
        }

        //Asignación de rol
        if (email.contains("@admin.com")){
            rolType = RolType.ADMIN;
        }

        //Transformar la fecha a LocalDate
        LocalDate birthdateChange = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        //Verifica que el dni no tenga puntos y no tenga menos de 7 digitos
        if(dni.length() <= 7 || dni.length() >= 9 || dni.contains(".")){
            return new ResponseEntity<>("El dni no es valido", HttpStatus.FORBIDDEN);
        }

        //Identificaion del clinte
        Client client = new Client(firstName,lastName, dni, email, passwordEncoder.encode(password),birthdateChange, LocalDate.now(), shift, rolType);

        //Guardado de cliente
        clientRepository.save(client);

        //----------------------------------

        return new ResponseEntity<>("Cliente registrado exitosamente",HttpStatus.CREATED);
    }

}
