package com.training.OnlineTraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExerciseDTO {
    private String name;
    private String description;
    private String equipmentNeeded;
    private String difficultyLevel;

}