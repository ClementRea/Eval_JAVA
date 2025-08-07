package com.clemRea.Tracker_entrainement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    @NotNull
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    @NotNull
    private Exercise exercise;

    @Positive(message = "Le nombre de séries doit être positif")
    @Column(nullable = false)
    private Integer sets;

    @Positive(message = "Le nombre de répétitions doit être positif")
    @Column(nullable = false)
    private Integer reps;

    private Double weight;

    private Integer restTime;

    private String notes;

    public WorkoutExercise() {}

    public WorkoutExercise(Workout workout, Exercise exercise, Integer sets, Integer reps, Double weight) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Workout getWorkout() { return workout; }
    public void setWorkout(Workout workout) { this.workout = workout; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public Integer getSets() { return sets; }
    public void setSets(Integer sets) { this.sets = sets; }

    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Integer getRestTime() { return restTime; }
    public void setRestTime(Integer restTime) { this.restTime = restTime; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Double getTotalVolume() {
        if (weight != null && sets != null && reps != null) {
            return sets * reps * weight;
        }
        return null;
    }
}