package com.spieren.spierengym;

import com.spieren.spierengym.models.*;
import com.spieren.spierengym.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class SpierenGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpierenGymApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, PaymentRepository paymentRepository,
									  RoutineRepository routineRepository,
									  DetailRoutineRepository detailRoutineRepository, ExerciseRepository exerciseRepository) {
		return (args) -> {
			//----------------------------------

			//Creacion de clientes
			Client client1 = new Client("Franco", "Galdame", "42793845", "franco23@gmail.com", passwordEncoder.encode("123"), LocalDate.of(2000,07, 05), LocalDate.now(), "9:00", RolType.CLIENT);
			Client client2 = new Client("Marcos", "Gomez","25362967","marcos@gmail.com", passwordEncoder.encode("123"), LocalDate.of(2001,12, 20), LocalDate.now(), "9:00", RolType.CLIENT);

			//Guardar clientes
			clientRepository.save(client1);
			clientRepository.save(client2);


			//-----------------------------------



			//Creacion de pagos
			Payment payment1 = new Payment(PaymentMethod.MP, LocalDate.now(), false, 4000.0);
			Payment payment2 = new Payment(PaymentMethod.EFECTIVO, LocalDate.now(), false, 4000.0);
			//Asignar pago a cliente
			client1.addPayment(payment1);
			client2.addPayment(payment2);
			//Guardar pago
			paymentRepository.save(payment1);
			paymentRepository.save(payment2);


			//----------------------------------


			//Creacion de rutina
			Routine routine1 = new Routine("definition",
					"Rutina para perder porsentajes de grasa y definir musculos", "Franco Galdame",
					"Definir musculos", Dificulty.INTERMEDIATE);
			Routine routine2 = new Routine("Hypertrofia", "crecimiento de musculos",
							"Manuel", "Crecimineto de musculos", Dificulty.INTERMEDIATE);
			//Asignacion
			client1.addRoutine(routine1);
			client2.addRoutine(routine2);
			//Guardar rutina
			routineRepository.save(routine1);
			routineRepository.save(routine2);


			//------------------------

			Exercise exercise1 = new Exercise("Press de banca", "Empujar barra con peso en banco",
											"Barra, banco", MuscleGroup.CHEST);
			Exercise exercise2 = new Exercise("Patada de burro", "jalar con el codo hacia atras",
											"jalar con el brazo hacia atras", MuscleGroup.TRICEPS);
			Exercise exercise3 = new Exercise("Sentadilla", "colocar barra en hombro y agacharse",
											"colocar barra en hombro", MuscleGroup.LEG);
			//Asignacion
			routine1.addExercise(exercise1);
			routine1.addExercise(exercise2);
			routine2.addExercise(exercise3);
			//Guardar ejercicio
			exerciseRepository.save(exercise1);
			exerciseRepository.save(exercise2);
			exerciseRepository.save(exercise3);




			//-------------------------------

			//Creacion de detalles de rutina
			Detail detail1 = new Detail(15, 40, 4);
			Detail detail2 = new Detail(15, 10, 4);
			Detail detail3 = new Detail(8,50,4);
			//Asigancion de detalles de rutina a ejercicios y rutina
			exercise1.addDetail(detail1);
			exercise2.addDetail(detail2);
			exercise3.addDetail(detail3);
			//Guardar detalles de rutina
			detailRoutineRepository.save(detail1);
			detailRoutineRepository.save(detail2);
			detailRoutineRepository.save(detail3);
		};
	}

}
