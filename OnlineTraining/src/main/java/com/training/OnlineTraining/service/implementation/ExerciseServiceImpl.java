package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.exceptions.ExerciseNotFoundException;
import com.training.OnlineTraining.mapper.ExerciseMapper;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.repository.ExerciseRepository;
import com.training.OnlineTraining.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;
    private static final Logger logger = LoggerFactory.getLogger(ExerciseServiceImpl.class);

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper exerciseMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseMapper = exerciseMapper;
        logger.info("ExerciseServiceImpl constructed.");
    }

    @Override
    public Exercise createExercise(ExerciseDTO exerciseDTO) {
        logger.info("Creating new exercise.");

        Exercise exercise = exerciseMapper.toExercise(exerciseDTO);
        Exercise savedExercise = exerciseRepository.save(exercise);

        logger.info("New exercise created.");

        return savedExercise;
    }

    @Override
    public Exercise getExerciseById(UUID id) {
        logger.info("Getting exercise by ID: {}", id);

        return exerciseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Exercise with ID {} not found.", id);
                    return new ExerciseNotFoundException("Exercise with ID " + id + " not found");
                });
    }

    @Override
    public List<Exercise> getAllExercises() {
        logger.info("Getting all exercises.");

        return exerciseRepository.findAll();
    }

    @Override
    public Exercise updateExercise(UUID id, ExerciseDTO exerciseDetails) {
        logger.info("Updating exercise with ID: {}", id);

        return exerciseRepository.findById(id)
                .map(exercise -> {
                    exercise.updateValues(exerciseDetails);
                    return exerciseRepository.save(exercise);
                })
                .orElseThrow(() -> {
                    logger.error("Exercise with ID {} not found.", id);
                    return new ExerciseNotFoundException("Exercise with ID " + id + " not found");
                });
    }

    @Override
    public void deleteExercise(UUID id) {
        logger.info("Deleting exercise with ID: {}", id);

        exerciseRepository.findById(id)
                .ifPresentOrElse(
                        exerciseRepository::delete,
                        () -> {
                            logger.error("Exercise with ID {} not found.", id);
                            throw new ExerciseNotFoundException("Exercise with ID " + id + " not found");
                        }
                );
    }

    @Override
    public void deleteAll() {
        logger.info("Deleting all exercises.");

        this.exerciseRepository.deleteAll();
    }
}
