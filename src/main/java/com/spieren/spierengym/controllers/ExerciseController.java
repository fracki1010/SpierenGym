package com.spieren.spierengym.controllers;

import com.spieren.spierengym.dtos.ExerciseDTO;
import com.spieren.spierengym.models.Exercise;
import com.spieren.spierengym.models.MuscleGroup;
import com.spieren.spierengym.models.Weekday;
import com.spieren.spierengym.repositories.DetailRepository;
import com.spieren.spierengym.repositories.ExerciseRepository;
import com.spieren.spierengym.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    DetailRepository detailRepository;

    @GetMapping("/exercises")
    public Set<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                                 .stream()
                                 .map(exercise -> new ExerciseDTO(exercise))
                                 .collect(Collectors.toSet());
    }

    @GetMapping("/exercises/{id}")
    public ExerciseDTO getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        return new ExerciseDTO(exerciseOptional.get());
    }

    @PostMapping("/exercises")
    public ResponseEntity<Object> creationExercise (@RequestParam String name, @RequestParam List<Weekday> weekdays,
                                                    @RequestParam String description, @RequestParam String instruction,
                                                    @RequestParam MuscleGroup muscle, @RequestParam Long routineId){

        //Verifica que ningun campo este vacio
        if(name.isBlank() || weekdays.isEmpty() || description.isBlank() || instruction.isBlank()){
            return new ResponseEntity<>("Por favor complete todos los campos", HttpStatus.FORBIDDEN);
        }

        //Verifica que no exista otro ejercicio con el mismo nombre
        if (exerciseRepository.existsByName(name)){
            return new ResponseEntity<>("Este ejercicio ya existe", HttpStatus.FORBIDDEN);
        }

        Exercise exercise = new Exercise(name, weekdays, description, instruction, muscle, null);

        //Verifica que se haya seleccionado una rutina existente
        if(!routineRepository.existsById(routineId)){
            return new ResponseEntity<>("Esta rutina no existe",HttpStatus.FORBIDDEN);
        }

        //Agregacion de rutina
        exercise.setRoutine(routineRepository.findById(routineId).orElse(null));

        exercise.setDetail(detailRepository.findByWeight(0));

        //Guardar ejercicio
        exerciseRepository.save(exercise);

        return new ResponseEntity<>("Exercise created", HttpStatus.OK);
    }

    @PostMapping("/exercises/edit/instruction")
    public ResponseEntity<Object> editInstruction(@RequestParam Long id, @RequestParam String newInstruction){
        Exercise exercise = exerciseRepository.findById(id).orElse(null);

        exercise.setInstructions(newInstruction);
        exerciseRepository.save(exercise);

        return new ResponseEntity<>("Instruccion Modificada con exito",HttpStatus.OK);
    }

    @PostMapping("/exercises/edit/description")
    public ResponseEntity<Object> editDescription(@RequestParam Long id, @RequestParam String newDescription){
        Exercise exercise = exerciseRepository.findById(id).orElse(null);

        exercise.setDescription(newDescription);
        exerciseRepository.save(exercise);

        return new ResponseEntity<>("Descripcion modificada con exito",HttpStatus.OK);
    }

    @PostMapping("/exercises/edit/muscle")
    public ResponseEntity<Object> editMuscle(@RequestParam Long id, @RequestParam MuscleGroup newMuscle){
        Exercise exercise = exerciseRepository.findById(id).orElse(null);

        exercise.setMuscleGroup(newMuscle);
        exerciseRepository.save(exercise);

        return new ResponseEntity<>("Musculo modificado con exito",HttpStatus.OK);
    }

    @PostMapping("/exercises/edit/name")
    public ResponseEntity<Object> editName(@RequestParam Long id, @RequestParam String newName){

        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        exercise.setName(newName);
        exerciseRepository.save(exercise);
        return new ResponseEntity<>("Nombre modificado con exito",HttpStatus.OK);
    }
}
