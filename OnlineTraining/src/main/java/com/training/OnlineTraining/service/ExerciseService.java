package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.input.ExerciseInputDTO;
import com.training.OnlineTraining.dto.output.ExerciseOutputDTO;
import com.training.OnlineTraining.model.Exercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciseService {
    ExerciseOutputDTO createExercise(ExerciseInputDTO exerciseInputDTO);
    Optional<ExerciseOutputDTO> getExerciseById(UUID id);
    List<ExerciseOutputDTO> getAllExercises();
    ExerciseOutputDTO updateExercise(UUID id, ExerciseInputDTO exerciseDetails);
    void deleteExercise(UUID id);
    void deleteAll();
}