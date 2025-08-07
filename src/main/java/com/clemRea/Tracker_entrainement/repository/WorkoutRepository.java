package com.clemRea.Tracker_entrainement.repository;

import com.clemRea.Tracker_entrainement.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Workout> findByNameContainingIgnoreCase(String name);

    List<Workout> findTop10ByOrderByDateDesc();

    @Query("SELECT w FROM Workout w WHERE YEAR(w.date) = :year AND MONTH(w.date) = :month ORDER BY w.date DESC")
    List<Workout> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(w) FROM Workout w WHERE w.date BETWEEN :start AND :end")
    Long countWorkoutsBetweenDates(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}