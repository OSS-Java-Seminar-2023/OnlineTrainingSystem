package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.exceptions.WorkoutSessionNotFoundException;
import com.training.OnlineTraining.mapper.WorkoutSessionMapper;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.repository.WorkoutSessionRepository;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.service.WorkoutSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutSessionServiceImpl implements WorkoutSessionService {

	private final WorkoutSessionRepository workoutSessionRepository;
	private final WorkoutSessionMapper workoutSessionMapper;
	private WorkoutService workoutService;
	private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionServiceImpl.class);

	public WorkoutSessionServiceImpl(WorkoutSessionRepository workoutSessionRepository, WorkoutSessionMapper workoutSessionMapper) {
		this.workoutSessionRepository = workoutSessionRepository;
		this.workoutSessionMapper = workoutSessionMapper;

		logger.info("WorkoutSessionServiceImpl constructed.");
	}

	@Autowired
	public void setWorkoutService(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}


	@Override
	public WorkoutSession createWorkoutSession(WorkoutSessionDTO workoutSessionDTO) {
		logger.info("Creating new workout session.");

		WorkoutSession workoutSession = workoutSessionMapper.toWorkoutSession(workoutSessionDTO);
		WorkoutSession savedWorkoutSession = workoutSessionRepository.save(workoutSession);

		workoutService.incrementNumberOfExercises(workoutSessionDTO.getWorkoutId());

		logger.info("New workout session created.");

		return savedWorkoutSession;
	}

	@Override
	public WorkoutSession getWorkoutSessionById(UUID id) {
		logger.info("Getting workout session by ID: {}", id);

		return workoutSessionRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout session with ID {} not found.", id);
					return new WorkoutSessionNotFoundException(id);
				});
	}

	@Override
	public List<WorkoutSession> getAllWorkoutSessions() {
		logger.info("Getting all workout sessions.");

		return workoutSessionRepository.findAll();
	}

	@Override
	public List<WorkoutSession> getAllByWorkoutId(UUID workoutID) {
		return workoutSessionRepository.findAllByWorkout_Id(workoutID);
	}

	@Override
	public WorkoutSession updateWorkoutSession(UUID id, WorkoutSessionDTO workoutSessionDetails) {
		logger.info("Updating workout session with ID: {}", id);

		WorkoutSession existingWorkoutSession = workoutSessionRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Workout session with ID {} not found.", id);
					return new WorkoutSessionNotFoundException(id);
				});

		WorkoutSession updatedWorkoutSession = workoutSessionMapper.toWorkoutSession(workoutSessionDetails);
		updatedWorkoutSession.setId(existingWorkoutSession.getId()); // Ensure the ID is preserved

		return workoutSessionRepository.save(updatedWorkoutSession);
	}

	@Override
	public void updateWorkoutSessions(List<WorkoutSession> workoutSessionList) {
		for(WorkoutSession workoutSession : workoutSessionList)
			updateWorkoutSession(workoutSession.getId(), workoutSessionMapper.toWorkoutSessionDTO(workoutSession));
	}

	@Override
	public void deleteWorkoutSession(UUID id) {
		logger.info("Deleting workout session with ID: {}", id);

		WorkoutSession workoutSession = getWorkoutSessionById(id);

		logger.info("Workout session {}", workoutSession);

		workoutService.decrementNumberOfExercises(workoutSession.getWorkout().getId());

		workoutSessionRepository.deleteById(id);
	}

	@Override
	public void deleteAllWorkoutSessions() {
		logger.info("Deleting all workout sessions.");

		workoutSessionRepository.deleteAll();
	}

	@Override
	public Optional<Exercise> getExerciseById(UUID workoutSessionId) {
		return workoutSessionRepository.findById(workoutSessionId)
				.map(WorkoutSession::getExercise);
	}

}
