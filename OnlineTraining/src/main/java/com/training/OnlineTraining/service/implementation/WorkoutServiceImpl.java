package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.repository.WorkoutRepository;
import com.training.OnlineTraining.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutServiceImpl implements WorkoutService {

	private final WorkoutRepository workoutRepository;

	public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
	}

	@Override
	public List<Workout> getWorkoutsByContractID(UUID contractID) {
		return workoutRepository.findByContractId(contractID);
	}
}
