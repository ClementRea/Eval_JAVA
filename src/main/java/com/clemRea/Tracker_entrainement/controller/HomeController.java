package com.clemRea.Tracker_entrainement.controller;

import com.clemRea.Tracker_entrainement.entity.Exercise;
import com.clemRea.Tracker_entrainement.entity.Workout;
import com.clemRea.Tracker_entrainement.repository.ExerciseRepository;
import com.clemRea.Tracker_entrainement.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur ton Tracker d'entraînement ! <br>" +
                "<a href='/exercises'>Voir les exercices</a><br>" +
                "<a href='/workouts'>Voir les séances</a><br>" +
                "<a href='/h2-console'>Console H2</a>";
    }

    @GetMapping("/api/status")
    public String status() {
        return "L'API est en route";
    }


    @GetMapping("/exercises")
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @GetMapping("/exercises/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        return exercise.orElse(null);
    }


    @GetMapping("/workouts")
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    @GetMapping("/workouts/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        return workout.orElse(null);
    }


    @GetMapping("/api/stats")
    public String getStats() {
        long exerciseCount = exerciseRepository.count();
        long workoutCount = workoutRepository.count();

        return "Statistiques :<br>" + exerciseCount + " exercices<br>" + workoutCount + " séances<br>";
    }
}