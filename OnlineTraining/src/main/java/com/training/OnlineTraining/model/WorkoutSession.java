package com.training.OnlineTraining.model;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "workout_session")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "workout_id", referencedColumnName = "id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = true)
    private Exercise exercise;

    @Column(name = "number_of_reps")
    private Integer numberOfReps;

    @Column(name = "pause_after_exercise_in_seconds")
    private Integer pauseAfterExerciseInSeconds;

    @Column
    private BigDecimal weight;

    @Override
    public String toString() {
        return "WorkoutSession{" +
                "exercise=" + exercise.getName() +
                ", numberOfReps=" + numberOfReps +
                ", pauseAfterExerciseInSeconds=" + pauseAfterExerciseInSeconds +
                ", weight=" + weight +
                '}';
    }
}
