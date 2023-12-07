package com.training.OnlineTraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {
    private String name;
    private String description;
    private String equipmentNeeded;
    private String difficultyLevel;

}