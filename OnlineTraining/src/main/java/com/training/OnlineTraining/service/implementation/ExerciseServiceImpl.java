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
    public Exercise getExerciseById(UUID id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise with ID " + id + " not found"));
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise updateExercise(UUID id, ExerciseDTO exerciseDetails) {
        return exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.updateValues(exerciseDetails);
                    return exerciseRepository.save(exercise);
                })
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise with ID " + id + " not found"));
    }


    @Override
    public void deleteExercise(UUID id) {
        exerciseRepository.findById(id)
                .ifPresentOrElse(
                        exerciseRepository::delete,
                        () -> {
                            throw new ExerciseNotFoundException("Exercise with ID " + id + " not found");
                        }
                );
    }

    @Override
    public void deleteAll() {
        this.exerciseRepository.deleteAll();
    }
}
