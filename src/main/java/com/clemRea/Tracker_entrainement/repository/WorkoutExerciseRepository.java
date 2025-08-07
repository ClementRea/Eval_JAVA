package com.clemRea.Tracker_entrainement.repository;

import com.clemRea.Tracker_entrainement.entity.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findByWorkoutId(Long workoutId);

    List<WorkoutExercise> findByExerciseIdOrderByWorkout_DateDesc(Long exerciseId);

    @Query("SELECT MAX(we.weight) FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId AND we.weight IS NOT NULL")
    Double findMaxWeightForExercise(@Param("exerciseId") Long exerciseId);

    @Query("SELECT SUM(we.sets * we.reps * COALESCE(we.weight, 0)) FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId")
    Double findTotalVolumeForExercise(@Param("exerciseId") Long exerciseId);

    @Query("SELECT we FROM WorkoutExercise we WHERE we.exercise.id = :exerciseId ORDER BY we.workout.date DESC")
    List<WorkoutExercise> findRecentPerformancesForExercise(@Param("exerciseId") Long exerciseId);
}