package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.enums.WorkoutGoal;
import com.training.OnlineTraining.model.enums.WorkoutType;
import com.training.OnlineTraining.service.WorkoutCreatorService;

import java.util.List;


public class WorkoutCreatorServiceImpl implements WorkoutCreatorService {


	@Override
	public Workout createWorkout(WorkoutType workoutType, WorkoutGoal workoutGoal, int numberOfSets) {

		return null;
	}

	private int getNumberOfReps(WorkoutGoal workoutGoal) {
		return switch (workoutGoal) {
			case STRENGTH -> 5;
			case ENDURANCE -> 20;
			case HYPERTROPHY -> 12;
			default -> {
				// Handle unknown workout goals or return a default value
				yield 0; // You can change this default value as needed
			}
		};
	}

	private List<Exercise> getListOfExerciseForWorkoutType(WorkoutType workoutType){
		return null;
	}




}
