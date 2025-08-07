package com.clemRea.Tracker_entrainement.repository;

import com.clemRea.Tracker_entrainement.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByNameContainingIgnoreCase(String name);

    List<Exercise> findByCategory(Exercise.Category category);

    Exercise findByNameIgnoreCase(String name);

    @Query("SELECT e FROM Exercise e JOIN e.workoutExercises we GROUP BY e ORDER BY COUNT(we) DESC")
    List<Exercise> findMostUsedExercises();
}