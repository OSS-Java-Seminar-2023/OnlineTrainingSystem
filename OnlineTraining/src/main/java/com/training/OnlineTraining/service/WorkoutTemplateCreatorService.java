package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.model.enums.WorkoutGoal;
import com.training.OnlineTraining.model.enums.WorkoutType;


public interface WorkoutTemplateCreatorService {

	WorkoutInputDTO createWorkout(WorkoutType workoutType, WorkoutGoal workoutGoal, int numberOfExercises);

}
