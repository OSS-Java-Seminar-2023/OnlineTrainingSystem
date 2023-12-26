package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.exceptions.WorkoutNotFoundException;
import com.training.OnlineTraining.mapper.WorkoutMapper;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.repository.WorkoutRepository;
import com.training.OnlineTraining.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	private final WorkoutRepository workoutRepository;
	private final WorkoutMapper workoutMapper;

	public WorkoutServiceImpl(WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
		this.workoutRepository = workoutRepository;
		this.workoutMapper = workoutMapper;
	}

	@Override
	public Workout createWorkout(WorkoutDTO workoutDTO) {
		Workout workout = workoutMapper.toWorkout(workoutDTO);
		return workoutRepository.save(workout);
	}

	@Override
	public Workout getWorkoutById(UUID id) {
		return workoutRepository.findById(id)
				.orElseThrow(() -> new WorkoutNotFoundException(id));
	}

	@Override
	public List<Workout> getAllWorkouts() {
		return workoutRepository.findAll();
	}

	@Override
	public Workout updateWorkout(UUID id, WorkoutDTO workoutDetails) {
		Workout existingWorkout = workoutRepository.findById(id)
				.orElseThrow(() -> new WorkoutNotFoundException(id));

		Workout updatedWorkout = workoutMapper.toWorkout(workoutDetails);
		updatedWorkout.setId(existingWorkout.getId()); // Ensure the ID is preserved
		return workoutRepository.save(updatedWorkout);
	}

	@Override
	public void deleteWorkout(UUID id) {
		workoutRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		workoutRepository.deleteAll();
	}

	@Override
	public List<Workout> getWorkoutsByContractID(UUID contractID) {
		return workoutRepository.findByContractId(contractID);
	}
}
