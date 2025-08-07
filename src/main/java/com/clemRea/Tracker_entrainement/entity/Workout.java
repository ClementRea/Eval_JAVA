package com.clemRea.Tracker_entrainement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "workouts")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date est obligatoire")
    @Column(nullable = false)
    private LocalDateTime date;

    private String name;

    private String notes;

    private Integer duration;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WorkoutExercise> workoutExercises;

    public Workout() {
        this.date = LocalDateTime.now();
    }

    public Workout(String name, String notes) {
        this();
        this.name = name;
        this.notes = notes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public List<WorkoutExercise> getWorkoutExercises() { return workoutExercises; }
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }
}