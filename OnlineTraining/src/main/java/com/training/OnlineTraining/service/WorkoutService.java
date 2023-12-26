package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.repository.WorkoutRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface WorkoutService {
	Workout createWorkout(WorkoutDTO workoutDTO);
	Workout getWorkoutById(UUID id);
	List<Workout> getAllWorkouts();
	Workout updateWorkout(UUID id, WorkoutDTO workoutDetails);
	void deleteWorkout(UUID id);
	void deleteAll();
	List<Workout> getWorkoutsByContractID(UUID contractID);
}
