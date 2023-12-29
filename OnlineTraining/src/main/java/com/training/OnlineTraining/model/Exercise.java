package com.training.OnlineTraining.model;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
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
    @Enumerated(EnumType.STRING)
    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    public void updateValues(ExerciseDTO exerciseDetails){
        this.setName(exerciseDetails.getName());
        this.setDescription(exerciseDetails.getDescription());
        this.setEquipmentNeeded(exerciseDetails.getEquipmentNeeded());
        this.setExerciseDifficultyLevel(exerciseDetails.getExerciseDifficultyLevel());
    }
}
