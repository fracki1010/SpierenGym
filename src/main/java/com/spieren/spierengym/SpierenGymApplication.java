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
import java.util.List;

@SpringBootApplication
public class SpierenGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpierenGymApplication.class, args);
	}

	/*@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, PaymentRepository paymentRepository,
									  RoutineRepository routineRepository,
									  DetailRepository detailRepository, ExerciseRepository exerciseRepository,
									  RankingRepository rankingRepository, WeightMaxRepository weightMaxRepository) {
		return (args) -> {
			//----------------------------------

			//Creacion de clientes
			Client client1 = new Client("Franco", "Galdame", "42793845", "2622517447", Gender.MASCULINO, "franco23@admin.com", passwordEncoder.encode("123"), LocalDate.of(2000,07, 05), LocalDate.now(), "9:00", RolType.ADMIN);
			Client client2 = new Client("Marcos", "Gomez","25362967", "2622522358", Gender.MASCULINO,"marcos@gmail.com", passwordEncoder.encode("123"), LocalDate.of(2001,12, 20), LocalDate.now(), "9:00", RolType.CLIENT);

			//Creacion de ranking
			Ranking ranking1 = new Ranking("Sentadilla",null , "/web/img/sentadilla.jpg");
			Ranking ranking2 = new Ranking("Press de banca" , null, "/web/img/press-de-banca.jpg");
			Ranking ranking3 = new Ranking("Prensa", null, "/web/img/prensa.jpg");

			//Guardar rankings
			rankingRepository.save(ranking1);
			rankingRepository.save(ranking2);
			rankingRepository.save(ranking3);


			//Asignacion de ranking al cliente y viceversa
			client1.addRankingAndClient(ranking1);
			client2.addRankingAndClient(ranking1);
			client1.addRankingAndClient(ranking2);
			client2.addRankingAndClient(ranking2);
			client2.addRankingAndClient(ranking3);

			//Guardar clientes
			clientRepository.save(client1);
			clientRepository.save(client2);



			//-----------------------------------



			//Creacion de pagos
			Payment payment1 = new Payment(LocalDate.now().plusMonths(1), false, 4000.0);
			Payment payment2 = new Payment(LocalDate.now().plusMonths(1), false, 4000.0);
			Payment payment3 = new Payment(LocalDate.now().plusMonths(2), false, 4000.0);
			Payment payment4 = new Payment(LocalDate.now().plusMonths(3), true, 5000.0);
			//Asignar pago a cliente
			client1.addPayment(payment1);
			client2.addPayment(payment2);
			client1.addPayment(payment3);
			client1.addPayment(payment4);
			//Guardar pago
			paymentRepository.save(payment1);
			paymentRepository.save(payment2);
			paymentRepository.save(payment3);
			paymentRepository.save(payment4);


			//----------------------------------


			//Creacion de rutina
			Routine routine1 = new Routine("Definition",
					"Rutina para perder porsentajes de grasa y definir musculos", "Franco Galdame",
					"Definir musculos");
			Routine routine2 = new Routine("Hypertrofia", "crecimiento de musculos",
							"Manuel", "Crecimineto de musculos");
			//Asignacion
			client1.addRoutine(routine1);
			client2.addRoutine(routine2);
			//Guardar rutina
			routineRepository.save(routine1);
			routineRepository.save(routine2);


			//-------------------------------

			//Creacion de detalles de rutina
			Detail detail1 = new Detail(15, 40, 4);
			Detail detail2 = new Detail(15, 10, 4);
			Detail detail3 = new Detail(8,50,4);
			Detail detail4 = new Detail(8,50,4);
			Detail detail5 = new Detail(0, 0, 0);


			//Guardar detalles de rutina
			detailRepository.save(detail1);
			detailRepository.save(detail2);
			detailRepository.save(detail3);
			detailRepository.save(detail4);
			detailRepository.save(detail5);

			//------------------------

			Exercise exercise1 = new Exercise("Press de banca", List.of(Weekday.MONDAY, Weekday.THURSDAY), "Empujar barra con peso en banco",
											"Barra, banco", MuscleGroup.CHEST, "img/press-de-banca.jpg");
			Exercise exercise2 = new Exercise("Patada de burro", List.of(Weekday.MONDAY), "jalar con el codo hacia atras",
											"jalar con el brazo hacia atras", MuscleGroup.TRICEPS, "img/patada-de-burro.jpg");
			Exercise exercise3 = new Exercise("Sentadilla", List.of(Weekday.WEDNESDAY, Weekday.FRIDAY), "colocar barra en hombro y agacharse",
											"colocar barra en hombro", MuscleGroup.LEG, "img/sentadilla.jpg");
			Exercise exercise4 = new Exercise("Press Banco Inclinado", List.of(Weekday.MONDAY,Weekday.THURSDAY), "descripcion...",
											"instrucciones...", MuscleGroup.CHEST, "img/press-banco-inclinado.jpg");
			Exercise exercise5 = new Exercise("Tricep Polea", List.of(Weekday.MONDAY, Weekday.FRIDAY), "descripcion...", "instrucciones...",
												MuscleGroup.TRICEPS, "img/triceps-polea.jpg");
			Exercise exercise6 = new Exercise("Press Frances", List.of(Weekday.MONDAY,Weekday.FRIDAY), "descripcion...", "instrucciones...",
												MuscleGroup.TRICEPS, "img/press-frances.jpg");
			Exercise exercise7 = new Exercise("Peso Muerto", List.of(Weekday.WEDNESDAY, Weekday.FRIDAY), "descripcion...", "instrucciones...",
												MuscleGroup.LEG, "img/peso-muerto.jpg");
			Exercise exercise8 = new Exercise("Bicep Banco", List.of(Weekday.TUESDAY, Weekday.FRIDAY), "descripcion...", "instrucciones...",
												MuscleGroup.BICEPS, "img/biceps-banco.jpg");
			Exercise exercise9 = new Exercise("Dorsales Agarre Prono", List.of(Weekday.TUESDAY, Weekday.THURSDAY), "descripcion...", "instrucciones...",
												MuscleGroup.BACK, "img/dorsales-agarre-prono.jpg");
			Exercise exercise10 = new Exercise("Prensa", List.of(Weekday.WEDNESDAY, Weekday.FRIDAY), "descripcion","instruccion",
												MuscleGroup.LEG, "img/prensa.jpg");

			//Asignacion rutina
			routine1.addExercise(exercise1);
			routine1.addExercise(exercise2);
			routine1.addExercise(exercise4);
			routine1.addExercise(exercise3);
			routine2.addExercise(exercise5);
			routine2.addExercise(exercise6);
			routine2.addExercise(exercise7);
			routine1.addExercise(exercise8);
			routine1.addExercise(exercise9);
			routine1.addExercise(exercise10);
			//Asignacion detalle
			detail1.addExercise(exercise1);
			detail2.addExercise(exercise2);
			detail3.addExercise(exercise3);
			detail3.addExercise(exercise4);
			detail3.addExercise(exercise5);
			detail3.addExercise(exercise6);
			detail1.addExercise(exercise7);
			detail1.addExercise(exercise8);
			detail1.addExercise(exercise9);
			detail1.addExercise(exercise10);


			//Guardar ejercicio
			exerciseRepository.save(exercise1);
			exerciseRepository.save(exercise2);
			exerciseRepository.save(exercise3);
			exerciseRepository.save(exercise4);
			exerciseRepository.save(exercise5);
			exerciseRepository.save(exercise6);
			exerciseRepository.save(exercise7);
			exerciseRepository.save(exercise8);
			exerciseRepository.save(exercise9);
			exerciseRepository.save(exercise10);


			//-----------------------------------------------------------

			//Agregar ejercicio a ranking
			ranking1.setExercise(exercise3);
			ranking2.setExercise(exercise1);
			ranking3.setExercise(exercise10);

			//Guardado del cambio
			rankingRepository.save(ranking1);
			rankingRepository.save(ranking2);
			rankingRepository.save(ranking3);


			//-----------------------------------------------------------

			//Creacion de WeightMax
			WeightMax weightMax1 = new WeightMax(100, exercise1, client1, LocalDate.now().minusDays(32));
			WeightMax weightMax2 = new WeightMax(140, exercise3, client1, LocalDate.now().minusDays(15));
			WeightMax weightMax3 = new WeightMax(150, exercise3, client2, LocalDate.now().minusDays(40));
			WeightMax weightMax4 = new WeightMax(95, exercise1, client2, LocalDate.now().minusDays(3));

			//Guardado de Pesos Maximos
			weightMaxRepository.save(weightMax1);
			weightMaxRepository.save(weightMax2);
			weightMaxRepository.save(weightMax3);
			weightMaxRepository.save(weightMax4);

			//Guardar a cliente
			clientRepository.save(client1);
			clientRepository.save(client2);

		};
	}
*/
}
