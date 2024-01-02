package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.exceptions.WorkoutNotFoundException;
import com.training.OnlineTraining.mapper.WorkoutMapper;
import com.training.OnlineTraining.mapper.WorkoutSessionMapper;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.additional.Duration;
import com.training.OnlineTraining.repository.WorkoutRepository;
import com.training.OnlineTraining.repository.WorkoutSessionRepository;
import com.training.OnlineTraining.service.WorkoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	private final WorkoutRepository workoutRepository;
	private final WorkoutMapper workoutMapper;

	private WorkoutSessionRepository workoutSessionRepository;
	private WorkoutSessionMapper workoutSessionMapper;

	private static final Logger logger = LoggerFactory.getLogger(WorkoutServiceImpl.class);

	public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper, WorkoutSessionRepository workoutSessionRepository, WorkoutSessionMapper workoutSessionMapper) {
		logger.info("WorkoutServiceImpl constructed.");

		this.workoutSessionRepository = workoutSessionRepository;
		this.workoutSessionMapper = workoutSessionMapper;
		this.workoutRepository = workoutRepository;
		this.workoutMapper = workoutMapper;
	}

	@Override
	public void createWorkout(WorkoutDTO workoutDTO, UUID contractID) {
		logger.info("Creating new workout.");

		int ordinalNumberOfLastWorkout = 0;

		Workout lastWorkout = workoutRepository.findTopByContractIdOrderByOrdinalNumberOfWorkoutDesc(contractID);

		if(lastWorkout != null) ordinalNumberOfLastWorkout = lastWorkout.getOrdinalNumberOfWorkout();


		workoutDTO.setDateOfWorkout(null);
		workoutDTO.setContractId(contractID);
		workoutDTO.setOrdinalNumberOfWorkout(++ordinalNumberOfLastWorkout);

		Workout savedWorkout = workoutRepository.save(workoutMapper.toWorkout(workoutDTO));

		for(int i = 0; i < workoutDTO.getNumberOfExercises(); ++i){
			WorkoutSessionDTO workoutSessionDTO = WorkoutSessionDTO.createEmptyWorkoutSessionDTO();
			workoutSessionDTO.setWorkoutId(savedWorkout.getId());

			workoutSessionRepository.save(workoutSessionMapper.toWorkoutSession(workoutSessionDTO));
		}

		logger.info("New workout created.");

	}
	@Override
	public Workout createWorkout(WorkoutDTO workoutDTO) {
		logger.info("Creating new workout. {}", workoutDTO);

		Workout workout = workoutMapper.toWorkout(workoutDTO);
		Workout savedWorkout = workoutRepository.save(workout);

		logger.info("New workout created.");

		return savedWorkout;
	}

	@Override
	public Workout getWorkoutById(UUID id) {
		logger.info("Getting workout by ID: {}", id);

		return workoutRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout with ID {} not found.", id);
					return new WorkoutNotFoundException(id);
				});
	}

	@Override
	public List<Workout> getAllWorkouts() {
		logger.info("Getting all workouts.");

		return workoutRepository.findAll();
	}

	@Override
	public Workout updateWorkout(UUID id, WorkoutDTO workoutDetails) {
		logger.info("Updating workout with ID: {}", id);

		Workout existingWorkout = workoutRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout with ID {} not found.", id);
					return new WorkoutNotFoundException(id);
				});

		Workout updatedWorkout = workoutMapper.toWorkout(workoutDetails);
		updatedWorkout.setId(existingWorkout.getId()); // Ensure the ID is preserved
		return workoutRepository.save(updatedWorkout);
	}

	@Override
	public Workout updateWorkout(Workout workout) {
		return workoutRepository.save(workout);
	}

	@Override
	public void incrementNumberOfExercises(UUID workoutID) {
		Workout tempWorkout = getWorkoutById(workoutID);
		tempWorkout.setNumberOfExercises(tempWorkout.getNumberOfExercises() + 1);
		workoutRepository.save(tempWorkout);
	}

	@Override
	public void decrementNumberOfExercises(UUID workoutID) {
		Workout tempWorkout = getWorkoutById(workoutID);
		tempWorkout.setNumberOfExercises(tempWorkout.getNumberOfExercises() - 1);
		workoutRepository.save(tempWorkout);
	}

	@Override
	public void updateWorkout(UUID id, WorkoutDTO workoutDetails, UUID contractID) {
		logger.info("Updating workout with ID: {}", id);

		workoutDetails.setContractId(contractID);

		Workout existingWorkout = workoutRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout with ID {} not found.", id);
					return new WorkoutNotFoundException(id);
				});

		Workout updatedWorkout = workoutMapper.toWorkout(workoutDetails);
		updatedWorkout.setId(existingWorkout.getId()); // Ensure the ID is preserved
		workoutRepository.save(updatedWorkout);
	}

	@Override
	public void deleteWorkout(UUID id) {
		logger.info("Deleting workout with ID: {}", id);

		workoutRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		logger.info("Deleting all workouts.");

		workoutRepository.deleteAll();
	}

	@Override
	public List<Workout> getWorkoutsByContractID(UUID contractID) {
		logger.info("Getting workouts by Contract ID: {}", contractID);

		return workoutRepository.findByContractId(contractID);
	}
}
