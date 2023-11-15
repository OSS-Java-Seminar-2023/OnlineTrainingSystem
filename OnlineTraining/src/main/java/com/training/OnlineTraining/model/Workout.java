package com.training.OnlineTraining.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @Column(name = "number_of_exercises")
    private Integer numberOfExercises;

    @Column(name = "warming_up_time_in_seconds")
    private Integer warmingUpTimeInSeconds;

    @Column(name = "number_of_sets")
    private Integer numberOfSets;

    @Column(name = "pause_between_sets_in_seconds")
    private Integer pauseBetweenSetsInSeconds;

    @Column(name = "self_rating")
    private Integer selfRating;
}
