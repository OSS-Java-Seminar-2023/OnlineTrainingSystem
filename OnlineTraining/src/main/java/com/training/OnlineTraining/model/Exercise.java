package com.training.OnlineTraining.model;

import com.training.OnlineTraining.dto.ExerciseDTO;
import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "equipment_needed")
    private String equipmentNeeded;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    public void updateValues(ExerciseDTO exerciseDetails){
        this.setName(exerciseDetails.getName());
        this.setDescription(exerciseDetails.getDescription());
        this.setEquipmentNeeded(exerciseDetails.getEquipmentNeeded());
        this.setDifficultyLevel(exerciseDetails.getDifficultyLevel());
    }
}
