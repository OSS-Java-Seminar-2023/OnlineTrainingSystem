package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.model.Exercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseService {
    Exercise createExercise(ExerciseDTO exerciseDTO);
    Optional<Exercise> getExerciseById(UUID id);
    List<Exercise> getAllExercises();
    Exercise updateExercise(UUID id, ExerciseDTO exerciseDetails);
    void deleteExercise(UUID id);

    void deleteAll();
}