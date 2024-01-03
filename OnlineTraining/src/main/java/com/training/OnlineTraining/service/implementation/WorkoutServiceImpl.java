package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.input.WorkoutSessionInputDTO;
import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.exceptions.WorkoutNotFoundException;
import com.training.OnlineTraining.exceptions.WorkoutSessionNotFoundException;
import com.training.OnlineTraining.mapper.WorkoutMapper;
import com.training.OnlineTraining.mapper.WorkoutSessionMapper;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.repository.WorkoutRepository;
import com.training.OnlineTraining.repository.WorkoutSessionRepository;
import com.training.OnlineTraining.service.WorkoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
	public void createWorkout(WorkoutInputDTO workoutInputDTO, UUID contractID) {
		logger.info("Creating new workout.");

		int ordinalNumberOfLastWorkout = 0;

		Workout lastWorkout = workoutRepository.findTopByContractIdOrderByOrdinalNumberOfWorkoutDesc(contractID);

		if(lastWorkout != null) ordinalNumberOfLastWorkout = lastWorkout.getOrdinalNumberOfWorkout();


		workoutInputDTO.setDateOfWorkout(null);
		workoutInputDTO.setContractId(contractID);
		workoutInputDTO.setOrdinalNumberOfWorkout(++ordinalNumberOfLastWorkout);

		Workout savedWorkout = workoutRepository.save(workoutMapper.toWorkout(workoutInputDTO));

		for(int i = 0; i < workoutInputDTO.getNumberOfExercises(); ++i){
			WorkoutSessionInputDTO workoutSessionInputDTO = WorkoutSessionInputDTO.createEmptyWorkoutSessionDTO();
			workoutSessionInputDTO.setWorkoutId(savedWorkout.getId());

			workoutSessionRepository.save(workoutSessionMapper.toWorkoutSession(workoutSessionInputDTO));
		}

		logger.info("New workout created.");

	}
	@Override
	public WorkoutOutputDTO createWorkout(WorkoutInputDTO workoutInputDTO) {
		logger.info("Creating new workout. {}", workoutInputDTO);

		Workout workout = workoutMapper.toWorkout(workoutInputDTO);
		Workout savedWorkout = workoutRepository.save(workout);

		logger.info("New workout created.");

		return workoutMapper.toWorkoutOutputDTO(savedWorkout);
	}

	@Override
	public WorkoutOutputDTO getWorkoutById(UUID id) {
		logger.info("Getting workout by ID: {}", id);


		return workoutRepository
				.findById(id)
				.map(workoutMapper::toWorkoutOutputDTO)
				.orElseThrow(() -> {
					logger.error("Workout with ID {} not found.", id);
					return new WorkoutNotFoundException(id);
				});
	}

	@Override
	public List<WorkoutOutputDTO> getAllWorkouts() {
		logger.info("Getting all workouts.");

		return workoutRepository
				.findAll()
				.stream()
				.map(workoutMapper::toWorkoutOutputDTO)
				.collect(Collectors.toList());
	}

	@Override
	public WorkoutOutputDTO updateWorkout(UUID id, WorkoutInputDTO workoutDetails) {
		logger.info("Updating workout with ID: {}", id);

		Workout existingWorkout = workoutRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout with ID {} not found.", id);
					return new WorkoutNotFoundException(id);
				});

		Workout updatedWorkout = workoutMapper.toWorkout(workoutDetails);
		updatedWorkout.setId(existingWorkout.getId()); // Ensure the ID is preserved

		return workoutMapper.toWorkoutOutputDTO(workoutRepository.save(updatedWorkout));
	}

	@Override
	public WorkoutOutputDTO updateWorkout(Workout workout) {
		return workoutMapper.toWorkoutOutputDTO(workoutRepository.save(workout));
	}

	@Override
	public void incrementNumberOfExercises(UUID workoutID) {
		WorkoutOutputDTO tempWorkout = getWorkoutById(workoutID);
		tempWorkout.setNumberOfExercises(tempWorkout.getNumberOfExercises() + 1);
		workoutRepository.save(workoutMapper.toWorkout(tempWorkout));
	}

	@Override
	public void decrementNumberOfExercises(UUID workoutID) {
		WorkoutOutputDTO tempWorkout = getWorkoutById(workoutID);
		tempWorkout.setNumberOfExercises(tempWorkout.getNumberOfExercises() - 1);
		workoutRepository.save(workoutMapper.toWorkout(tempWorkout));
	}

	@Override
	public void updateWorkout(UUID id, WorkoutInputDTO workoutDetails, UUID contractID) {
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
	public List<WorkoutOutputDTO> getWorkoutsByContractID(UUID contractID) {
		logger.info("Getting workouts by Contract ID: {}", contractID);

		return workoutRepository
				.findByContractId(contractID)
				.stream()
				.map(workoutMapper::toWorkoutOutputDTO)
				.collect(Collectors.toList());
	}
}
