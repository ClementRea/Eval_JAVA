package com.clemRea.Tracker_entrainement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de l'exercice est obligatoire")
    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<WorkoutExercise> workoutExercises;

    public Exercise() {}

    public Exercise(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<WorkoutExercise> getWorkoutExercises() { return workoutExercises; }
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }

    public enum Category {
        CHEST("Pectoraux"),
        BACK("Dos"),
        LEGS("Jambes"),
        SHOULDERS("Épaules"),
        ARMS("Bras"),
        CORE("Abdominaux"),
        CARDIO("Cardio");

        private final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() { return displayName; }
    }
}