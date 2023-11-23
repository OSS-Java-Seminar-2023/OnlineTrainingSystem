package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.exceptions.ExerciseNotFoundException;
import com.training.OnlineTraining.mapper.ExerciseMapper;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.repository.ExerciseRepository;
import com.training.OnlineTraining.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    private final ExerciseMapper exerciseMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public Exercise createExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseMapper.toExercise(exerciseDTO);
        return exerciseRepository.save(exercise);
    }

    @Override
    public Optional<Exercise> getExerciseById(UUID id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise updateExercise(UUID id, ExerciseDTO exerciseDetails) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if (optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            exercise.setName(exerciseDetails.getName());
            exercise.setDescription(exerciseDetails.getDescription());
            exercise.setEquipmentNeeded(exerciseDetails.getEquipmentNeeded());
            exercise.setDifficultyLevel(exerciseDetails.getDifficultyLevel());
            return exerciseRepository.save(exercise);
        } else {
            throw new ExerciseNotFoundException("Exercise with ID " + id + " not found");
        }
    }

    @Override
    public void deleteExercise(UUID id) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if (optionalExercise.isPresent()) {
            exerciseRepository.delete(optionalExercise.get());
        } else {
            throw new ExerciseNotFoundException("Exercise with ID " + id + " not found");
        }
    }
}
