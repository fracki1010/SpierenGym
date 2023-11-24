package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ExerciseDTO;
import com.spieren.spierengym.dtos.RoutineDTO;
import com.spieren.spierengym.models.Client;
import com.spieren.spierengym.models.Exercise;
import com.spieren.spierengym.models.Routine;
import com.spieren.spierengym.models.Weekday;
import com.spieren.spierengym.repositories.ClientRepository;
import com.spieren.spierengym.repositories.ExerciseRepository;
import com.spieren.spierengym.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RoutineController {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping("/routines")
    public Set<RoutineDTO> getAllRoutines() {
        Set<RoutineDTO> allRoutine = routineRepository.findAll()
                                                      .stream()
                                                      .map(routine -> new RoutineDTO(routine))
                                                      .collect(Collectors.toSet());
        return allRoutine;
    }

    @GetMapping("/routines/client/current")
    public RoutineDTO getCurrentRoutine(Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        Routine routine = routineRepository.findById(client.getRoutines().stream().findFirst().get().getId()).orElse(null);
        return new RoutineDTO(routine);
    }

    @GetMapping("/routines/weekday/{id}")
    public Set<ExerciseDTO> getRoutineById(@PathVariable int id, Authentication authentication) {
        Client client = clientRepository.findByEmail(authentication.getName());
        //Verifica en que dia se selecciono para enviar los ejercicios
        Weekday day = null;
        switch (id) {
            case 1:
                day = Weekday.MONDAY;
                break;
            case 2:
                day = Weekday.TUESDAY;
                break;
            case 3:
                day = Weekday.WEDNESDAY;
                break;
            case 4:
                day = Weekday.THURSDAY;
                break;
            case 5:
                day = Weekday.FRIDAY;
                break;
        }
        Weekday finalDay = day;

        Routine routine = client.getRoutines().stream().findFirst().get();
        Set<Exercise> exercisesDay = routine.getExercises().stream().filter(exercise -> exercise.getWeekdays().contains(finalDay)).collect(Collectors.toSet());
        return exercisesDay.stream().map(exercise -> new ExerciseDTO(exercise)).collect(Collectors.toSet());
    }

    @GetMapping("/routines/{id}")
    public RoutineDTO getRoutineById(@PathVariable Long id) {
        Optional<Routine> routineOptional = routineRepository.findById(id);
        return new RoutineDTO(routineOptional.get());
    }

    @PostMapping("/routine")
    public ResponseEntity<Object> creationRoutine(@RequestParam String name, @RequestParam String description,
                                                  @RequestParam String creator, @RequestParam String objective,
                                                  @RequestParam List<Long> exercises){
        //Verifica que el que lo esta creando sea un administrador
/*        if(!clientRepository.findByEmail(authentication.getName()).getRol().equals("ADMIN")){
            return new ResponseEntity<>("No se pudo realizar, usted no es administrador", HttpStatus.FORBIDDEN);
        }*/

        //Verifica que todos los campos esten llenos
        if (name.isBlank() ||  description.isBlank() || creator.isBlank() || objective.isBlank()){
            return new ResponseEntity<>("Por favor complete todos los campos", HttpStatus.FORBIDDEN);
        }

        //Verifica que la rutina no exista
        if (routineRepository.existsByName(name)){
            return new ResponseEntity<>("Esta rutina ya fue creada", HttpStatus.FORBIDDEN);
        }

        //Creacion de rutina
        Routine routine = new Routine(name, description, creator, objective);


        //Agregar ejercicios
        for (Long exerciseId : exercises){
            Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
            if (exercise != null){
                routine.addExercise(exercise);
                //Guardar ejercicio
                exercise.setRoutine(routine);
            }
        }



        //Guardar rutina
        routineRepository.save(routine);

        return new ResponseEntity<>("Rutina creada con exito", HttpStatus.OK);
    }

}
