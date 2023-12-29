package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
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
    private String equipmentNeeded;
    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    public ExerciseDTO(String name, String description, String equipmentNeeded, ExerciseDifficultyLevel exerciseDifficultyLevel) {
        this.name = name;
        this.description = description;
        this.equipmentNeeded = equipmentNeeded;
        this.exerciseDifficultyLevel = exerciseDifficultyLevel;
    }
}