package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
import com.training.OnlineTraining.model.enums.ExerciseEquipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseDTO {
    private UUID id;
    private String name;
    private String description;
    private ExerciseEquipment exerciseEquipment;
    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    public ExerciseDTO(String name, String description, ExerciseEquipment exerciseEquipment, ExerciseDifficultyLevel exerciseDifficultyLevel) {
        this.name = name;
        this.description = description;
        this.exerciseEquipment = exerciseEquipment;
        this.exerciseDifficultyLevel = exerciseDifficultyLevel;
    }
}