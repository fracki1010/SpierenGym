package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ClientDTO;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Gender;
import com.spieren.spierengym.models.Payment;
import com.spieren.spierengym.models.RolType;
import com.spieren.spierengym.repositories.ClientRepository;
import com.spieren.spierengym.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping("/clients")
    public List<ClientDTO> getAllClient() {
        List<ClientDTO> allClients = clientRepository
                                        .findAll()
                                        .stream()
                                        .map(client -> new ClientDTO(client))
                                        .collect(Collectors.toList());
        return allClients;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        Client client = clientRepository.findById(id).orElse(null);
        return new ClientDTO(client);
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> register(String firstName, String lastName,
                                           String email, String password, RolType rolType,
                                           String dni, String birthdate, String phone,
                                           Gender gender, Authentication authentication){

        if (firstName.isBlank() || lastName.isBlank()||email.isBlank()||password.isBlank() || dni.isBlank() || birthdate.isBlank() || phone.isBlank()){
            return new ResponseEntity<>("Por favor completa todo los datos", HttpStatus.FORBIDDEN);
        }

        if(clientRepository.findByEmail(email) !=null){
            return new ResponseEntity<>("Este email ya fue utilizado", HttpStatus.FORBIDDEN);
        }

        //Verifica que el dni sea unico en la base de datos
        if (clientRepository.existsByDni(dni)){
            return new ResponseEntity<>("Esta persona ya esta registrada", HttpStatus.FORBIDDEN);
        }

        //Verifica que el numero de celular no exista en la base de datos
        if (clientRepository.existsByPhone(phone)){
            return new ResponseEntity<>("Este número ya existe", HttpStatus.FORBIDDEN);
        }

        //Verifica que el campo genero no este vacio
        if (gender == Gender.NONE){
            return new ResponseEntity<>("Complete el campo de genero", HttpStatus.FORBIDDEN);
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
        Client client = new Client(firstName,lastName, dni, phone, gender, email, passwordEncoder.encode(password),birthdateChange, LocalDate.now(), "no asignado", rolType);

        //Guardado de cliente
        clientRepository.save(client);

        //Creacion de pago del mes
        Payment payment = new Payment(LocalDate.now().plusMonths(1), false, 3800.0);

        //Asignacion de pago a cliente
        client.addPayment(payment);

        //Guardar pago
        paymentRepository.save(payment);

        //----------------------------------

        return new ResponseEntity<>("Cliente registrado exitosamente",HttpStatus.CREATED);
    }

    @GetMapping("/clients/current")
    public ClientDTO getClientCurrent(Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());
        return new ClientDTO(client);
    }
}
