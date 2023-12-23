package com.training.OnlineTraining.dto;

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
    private String difficultyLevel;

}