package com.spieren.spierengym;

import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpierenGymApplication <commandLineRunner> {

	public static void main(String[] args) {
		SpringApplication.run(SpierenGymApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository) {
		return (arg) -> {
			Client client1 = new Client("Franco", "Galdame", "franco23@gmail.com", "Colonia Las Rosas", LocalDate.now(), LocalDate.of(2000,07, 05),LocalDate.now());

			clientRepository.save(client1);
		};
	}

}
