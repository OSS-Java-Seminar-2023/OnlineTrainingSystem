package com.training.OnlineTraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "equipment_needed", nullable = false)
    private String equipmentNeeded;

    @Column(name = "difficulty_level", nullable = false, length = 30)
    private String difficultyLevel;
}
