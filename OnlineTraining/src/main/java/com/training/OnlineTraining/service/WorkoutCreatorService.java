package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.enums.WorkoutGoal;
import com.training.OnlineTraining.model.enums.WorkoutType;


public interface WorkoutCreatorService {

	Workout createWorkout(WorkoutType workoutType, WorkoutGoal workoutGoal, int numberOfSets);

}
