package com.training.OnlineTraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "WorkoutSession")
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "workout_id", referencedColumnName = "id", nullable = false)
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false)
    private Exercise exercise;

    @Column(name = "number_of_reps", nullable = false)
    private Integer numberOfReps;

    @Column(name = "pause_after_exercise_in_seconds", nullable = false)
    private Integer pauseAfterExerciseInSeconds;

    @Column(name = "weight", nullable = false, precision = 6, scale = 2)
    private BigDecimal weight;
}
