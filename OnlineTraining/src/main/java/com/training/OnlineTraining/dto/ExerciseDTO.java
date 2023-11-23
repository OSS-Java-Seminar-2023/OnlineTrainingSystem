package com.training.OnlineTraining.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private String name;
    private String description;
    private String equipmentNeeded;
    private String difficultyLevel;
}