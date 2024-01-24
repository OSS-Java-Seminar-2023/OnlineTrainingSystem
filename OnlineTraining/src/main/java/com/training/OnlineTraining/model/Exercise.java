package com.training.OnlineTraining.model;

import com.training.OnlineTraining.dto.input.ExerciseInputDTO;
import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
import com.training.OnlineTraining.model.enums.ExerciseEquipment;
import com.training.OnlineTraining.model.enums.WorkoutType;
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
    @Enumerated(EnumType.STRING)
    private ExerciseEquipment exerciseEquipment;

    @Column(name = "difficulty_level")
    @Enumerated(EnumType.STRING)
    private ExerciseDifficultyLevel exerciseDifficultyLevel;

    @Column(name = "workout_type")
    @Enumerated(EnumType.STRING)
    private WorkoutType workoutType;


}
