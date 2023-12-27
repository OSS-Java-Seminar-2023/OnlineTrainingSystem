package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.model.Workout;

import java.util.List;
import java.util.UUID;

public interface WorkoutService {
	void createWorkout(WorkoutDTO workoutDTO, UUID contractID);
	Workout createWorkout(WorkoutDTO workoutDTO);
	Workout getWorkoutById(UUID id);
	List<Workout> getAllWorkouts();
	Workout updateWorkout(UUID id, WorkoutDTO workoutDetails);
	Workout updateWorkout(Workout workout);
	void updateWorkout(UUID id, WorkoutDTO workoutDetails, UUID contractID);
	void deleteWorkout(UUID id);
	void deleteAll();
	List<Workout> getWorkoutsByContractID(UUID contractID);
}
