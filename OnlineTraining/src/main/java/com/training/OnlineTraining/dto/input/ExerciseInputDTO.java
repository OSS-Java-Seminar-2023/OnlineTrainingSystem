package com.training.OnlineTraining.dto.input;

import com.training.OnlineTraining.converter.ExerciseEquipmentConverter;
import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
import com.training.OnlineTraining.model.enums.ExerciseEquipment;
import jakarta.persistence.Convert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@ToString
public class ExerciseInputDTO {
    private String name;
    private String description;

    @Convert(converter = ExerciseEquipmentConverter.class)
    private ExerciseEquipment exerciseEquipment;

    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    public ExerciseInputDTO(String name, String description, ExerciseEquipment exerciseEquipment, ExerciseDifficultyLevel exerciseDifficultyLevel) {
        this.name = name;
        this.description = description;
        this.exerciseEquipment = exerciseEquipment;
        this.exerciseDifficultyLevel = exerciseDifficultyLevel;
    }
}