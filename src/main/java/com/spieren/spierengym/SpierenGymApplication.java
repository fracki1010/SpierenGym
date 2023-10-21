package com.spieren.spierengym;

import com.spieren.spierengym.models.*;
import com.spieren.spierengym.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpierenGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpierenGymApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, PaymentRepository paymentRepository,
									  RoutineRepository routineRepository, ActivityRepository activityRepository,
									  DetailRoutineRepository detailRoutineRepository, ExerciseRepository exerciseRepository) {
		return (args) -> {
			//------------------
			//Creacion de clientes
			Client client1 = new Client("Franco", "Galdame", "franco23@gmail.com", "Colonia Las Rosas", LocalDate.now(), LocalDate.of(2000,07, 05),LocalDate.now());

			//Guardar clientes
			clientRepository.save(client1);

			//-------------------------

			//Creacion de pagos
			Payment payment1 = new Payment(PaymentMethod.MP, LocalDate.now(), false, 4000.0);

			//Asignar pago a cliente
			client1.addPayment(payment1);
			//Guardar pago
			paymentRepository.save(payment1);



			//----------------------------------


			//Creacion de rutina
			Routine routine1 = new Routine("definition",
					"Rutina para perder porsentajes de grasa y definir musculos", "Franco Galdame",
					"Definir musculos", Dificulty.INTERMEDIATE);

			//Asignacion de rutina a actividades

			//Guardar rutina
			routineRepository.save(routine1);

			//------------------------


			//Creacion de actividad
			Activity activity1 = new Activity("11:00", client1, routine1);
			//Asignacion de activid

			//Guardar actividad
			activityRepository.save(activity1);

			//----------------------------------

			Exercise exercise1 = new Exercise("Press de banca", "Empujar barra con peso en banco",
											"Barra, banco", MuscleGroup.CHEST);
			//Guardar ejercicio
			exerciseRepository.save(exercise1);

			//-------------------------------

			//Creacion de detalles de rutina
			DetailRoutine detailRoutine1 = new DetailRoutine(12, 30, 4);
			//Asigancion de detalles de rutina a ejercicios y rutina
			exercise1.addDetailRoutine(detailRoutine1);
			routine1.addDetailRoutine(detailRoutine1);
			//Guardar detalles de rutina
			detailRoutineRepository.save(detailRoutine1);
		};
	}

}
