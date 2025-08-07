package com.clemRea.Tracker_entrainement.controller;

import com.clemRea.Tracker_entrainement.entity.Exercise;
import com.clemRea.Tracker_entrainement.entity.Workout;
import com.clemRea.Tracker_entrainement.repository.ExerciseRepository;
import com.clemRea.Tracker_entrainement.repository.WorkoutRepository;
import com.clemRea.Tracker_entrainement.repository.WorkoutExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @GetMapping("/web")
    public String home(Model model) {
        model.addAttribute("exerciseCount", exerciseRepository.count());
        model.addAttribute("workoutCount", workoutRepository.count());
        return "index";
    }

    @GetMapping("/web/exercises")
    public String listExercises(Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "exercises";
    }

    @GetMapping("/web/exercises/{id}")
    public String exerciseDetail(@PathVariable Long id, Model model) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            model.addAttribute("exercise", exercise.get());
            // Récupérer l'historique de cet exercice
            model.addAttribute("history", workoutExerciseRepository.findByExerciseIdOrderByWorkout_DateDesc(id));
        }
        return "exercise-detail";
    }

    @GetMapping("/web/workouts")
    public String listWorkouts(Model model) {
        model.addAttribute("workouts", workoutRepository.findAll());
        return "workouts";
    }

    @GetMapping("/web/workouts/{id}")
    public String workoutDetail(@PathVariable Long id, Model model) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            model.addAttribute("workout", workout.get());
            // Récupérer tous les exercices de cette séance
            model.addAttribute("exercises", workoutExerciseRepository.findByWorkoutId(id));
        }
        return "workout-detail";
    }


    @GetMapping("/web/exercises/add")
    public String addExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("categories", Exercise.Category.values());
        return "exercise-form";
    }

    @PostMapping("/web/exercises/add")
    public String addExercise(@ModelAttribute Exercise exercise, RedirectAttributes redirectAttributes) {
        exerciseRepository.save(exercise);
        redirectAttributes.addFlashAttribute("success", "Exercice ajouté avec succès !");
        return "redirect:/web/exercises";
    }

    @GetMapping("/web/exercises/edit/{id}")
    public String editExerciseForm(@PathVariable Long id, Model model) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            model.addAttribute("exercise", exercise.get());
            model.addAttribute("categories", Exercise.Category.values());
            return "exercise-form";
        }
        return "redirect:/web/exercises";
    }

    @PostMapping("/web/exercises/edit/{id}")
    public String editExercise(@PathVariable Long id, @ModelAttribute Exercise exercise, RedirectAttributes redirectAttributes) {
        exercise.setId(id);
        exerciseRepository.save(exercise);
        redirectAttributes.addFlashAttribute("success", "Exercice modifié avec succès !");
        return "redirect:/web/exercises";
    }

    @PostMapping("/web/exercises/delete/{id}")
    public String deleteExercise(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        exerciseRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Exercice supprimé avec succès !");
        return "redirect:/web/exercises";
    }


    @GetMapping("/web/workouts/add")
    public String addWorkoutForm(Model model) {
        model.addAttribute("workout", new Workout());
        return "workout-form";
    }

    @PostMapping("/web/workouts/add")
    public String addWorkout(@ModelAttribute Workout workout, RedirectAttributes redirectAttributes) {
        workoutRepository.save(workout);
        redirectAttributes.addFlashAttribute("success", "Séance ajoutée avec succès !");
        return "redirect:/web/workouts";
    }

    @GetMapping("/web/workouts/edit/{id}")
    public String editWorkoutForm(@PathVariable Long id, Model model) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            model.addAttribute("workout", workout.get());
            return "workout-form";
        }
        return "redirect:/web/workouts";
    }

    @PostMapping("/web/workouts/edit/{id}")
    public String editWorkout(@PathVariable Long id, @ModelAttribute Workout workout, RedirectAttributes redirectAttributes) {
        workout.setId(id);
        workoutRepository.save(workout);
        redirectAttributes.addFlashAttribute("success", "Séance modifiée avec succès !");
        return "redirect:/web/workouts";
    }

    @PostMapping("/web/workouts/delete/{id}")
    public String deleteWorkout(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        workoutRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Séance supprimée avec succès !");
        return "redirect:/web/workouts";
    }
}