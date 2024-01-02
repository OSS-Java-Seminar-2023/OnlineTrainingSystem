package com.training.OnlineTraining.model;

import com.training.OnlineTraining.converter.ExerciseEquipmentConverter;
import com.training.OnlineTraining.dto.input.ExerciseInputDTO;
import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
import com.training.OnlineTraining.model.enums.ExerciseEquipment;
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

    @Column(name = "exercise_equipment")
    @Convert(converter = ExerciseEquipmentConverter.class)
    private ExerciseEquipment exerciseEquipment;

    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    public void updateValues(ExerciseInputDTO exerciseDetails){
        this.setName(exerciseDetails.getName());
        this.setDescription(exerciseDetails.getDescription());
        this.setExerciseEquipment(exerciseDetails.getExerciseEquipment());
        this.setExerciseDifficultyLevel(exerciseDetails.getExerciseDifficultyLevel());
    }
}
