package com.training.OnlineTraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @Column(name = "number_of_exercises", nullable = false)
    private Integer numberOfExercises;

    @Column(name = "warming_up_time_in_seconds", nullable = false)
    private Integer warmingUpTimeInSeconds;

    @Column(name = "number_of_sets", nullable = false)
    private Integer numberOfSets;

    @Column(name = "pause_between_sets_in_seconds", nullable = false)
    private Integer pauseBetweenSetsInSeconds;

    @Column(name = "self_rating")
    private Integer selfRating;
}
