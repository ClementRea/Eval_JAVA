package com.clemRea.Tracker_entrainement.service;

import com.clemRea.Tracker_entrainement.entity.Exercise;
import com.clemRea.Tracker_entrainement.entity.Workout;
import com.clemRea.Tracker_entrainement.entity.WorkoutExercise;
import com.clemRea.Tracker_entrainement.repository.ExerciseRepository;
import com.clemRea.Tracker_entrainement.repository.WorkoutRepository;
import com.clemRea.Tracker_entrainement.repository.WorkoutExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Override
    public void run(String... args) throws Exception {
        if (exerciseRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        Exercise squats = new Exercise("Squats", "Flexion des jambes avec charge", Exercise.Category.LEGS);
        Exercise benchPress = new Exercise("Développé couché", "Exercice pour les pectoraux", Exercise.Category.CHEST);
        Exercise deadlift = new Exercise("Soulevé de terre", "Exercice complet dos/jambes", Exercise.Category.BACK);
        Exercise pullUps = new Exercise("Tractions", "Exercice au poids du corps pour le dos ou les biceps", Exercise.Category.BACK);
        Exercise pushUps = new Exercise("Pompes", "Exercice au poids du corps pour les pectoraux, triceps", Exercise.Category.CHEST);
        Exercise plank = new Exercise("Gainage", "Exercice pour les abdominaux", Exercise.Category.CORE);
        Exercise shoulderPress = new Exercise("Développé militaire", "Exercice pour les épaules", Exercise.Category.SHOULDERS);
        Exercise bicepCurls = new Exercise("Curl biceps", "Exercice d'isolation des biceps", Exercise.Category.ARMS);

        exerciseRepository.save(squats);
        exerciseRepository.save(benchPress);
        exerciseRepository.save(deadlift);
        exerciseRepository.save(pullUps);
        exerciseRepository.save(pushUps);
        exerciseRepository.save(plank);
        exerciseRepository.save(shoulderPress);
        exerciseRepository.save(bicepCurls);

        Workout workout1 = new Workout("Séance Pectoraux/Triceps", "Bonne séance, progression sur le développé couché");
        workout1.setDate(LocalDateTime.now().minusDays(3));
        workout1.setDuration(75);
        workoutRepository.save(workout1);

        Workout workout2 = new Workout("Séance Dos/Biceps", "Séance intense, nouveau record sur les tractions");
        workout2.setDate(LocalDateTime.now().minusDays(1));
        workout2.setDuration(80);
        workoutRepository.save(workout2);

        Workout workout3 = new Workout("Séance Jambes", "Séance complète des jambes");
        workout3.setDate(LocalDateTime.now().minusDays(5));
        workout3.setDuration(90);
        workoutRepository.save(workout3);

        WorkoutExercise we1 = new WorkoutExercise(workout1, benchPress, 4, 8, 80.0);
        we1.setRestTime(120);
        we1.setNotes("Bonne forme, augmenter le poids la prochaine fois");
        workoutExerciseRepository.save(we1);

        WorkoutExercise we2 = new WorkoutExercise(workout1, pushUps, 3, 15, null);
        we2.setRestTime(60);
        workoutExerciseRepository.save(we2);

        WorkoutExercise we3 = new WorkoutExercise(workout2, pullUps, 4, 6, null);
        we3.setRestTime(90);
        we3.setNotes("Nouveau record personnel !");
        workoutExerciseRepository.save(we3);

        WorkoutExercise we4 = new WorkoutExercise(workout2, bicepCurls, 3, 12, 15.0);
        we4.setRestTime(60);
        workoutExerciseRepository.save(we4);

        WorkoutExercise we5 = new WorkoutExercise(workout2, deadlift, 3, 5, 100.0);
        we5.setRestTime(180);
        we5.setNotes("Technique parfaite");
        workoutExerciseRepository.save(we5);

        WorkoutExercise we6 = new WorkoutExercise(workout3, squats, 4, 10, 70.0);
        we6.setRestTime(120);
        we6.setNotes("Descente contrôlée");
        workoutExerciseRepository.save(we6);

        WorkoutExercise we7 = new WorkoutExercise(workout3, plank, 3, 1, null);
        we7.setRestTime(60);
        we7.setNotes("Tenir 45 secondes par série");
        workoutExerciseRepository.save(we7);

        System.out.println("Données initiales créées");
        System.out.println(exerciseRepository.count() + " exercices créés");
        System.out.println(workoutRepository.count() + " séances créées");
        System.out.println(workoutExerciseRepository.count() + " exercices de séance créés");
    }
}