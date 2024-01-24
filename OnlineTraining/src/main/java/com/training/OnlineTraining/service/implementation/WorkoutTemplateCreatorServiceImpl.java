package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.output.ExerciseOutputDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.enums.WorkoutGoal;
import com.training.OnlineTraining.model.enums.WorkoutType;
import com.training.OnlineTraining.service.ExerciseService;
import com.training.OnlineTraining.service.WorkoutTemplateCreatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class WorkoutTemplateCreatorServiceImpl implements WorkoutTemplateCreatorService {


	private final ExerciseService exerciseService;

	@Override
	public WorkoutInputDTO createWorkout(WorkoutType workoutType, WorkoutGoal workoutGoal, int numberOfExercises) {

		return WorkoutInputDTO.builder()
				.numberOfExercises(numberOfExercises)
				.warmingUpTimeInSeconds(300)
				.numberOfSets(getNumberOfSets(workoutGoal))
				.pauseBetweenSetsInSeconds(getPauseBetweenSetsInSeconds(workoutGoal))
				.workoutSessions(getWorkoutSessions(workoutType, workoutGoal, numberOfExercises))
				.build();
	}

	private int getNumberOfSets(WorkoutGoal workoutGoal) {
		return switch (workoutGoal) {
			case STRENGTH -> 4;
			case ENDURANCE -> 8;
			case HYPERTROPHY -> 6;
		};
	}

	private int getPauseBetweenSetsInSeconds(WorkoutGoal workoutGoal) {
		return switch (workoutGoal) {
			case STRENGTH -> 240;
			case ENDURANCE -> 120;
			case HYPERTROPHY -> 180;
		};
	}

	private List<WorkoutSession> getWorkoutSessions(WorkoutType workoutType, WorkoutGoal workoutGoal, int numberOfExercises){
		List<Exercise> listOfExercisesForWorkoutType = getExercisesForWorkoutType(workoutType, numberOfExercises);


		return listOfExercisesForWorkoutType.stream()
				.map(exercise -> WorkoutSession.builder()
						.exercise(exercise)
						.numberOfReps(getNumberOfReps(workoutGoal))
						.pauseAfterExerciseInSeconds(getRestAfterExerciseInSeconds(workoutGoal))
						.build())
				.collect(Collectors.toList());
	}

	private List<Exercise> getExercisesForWorkoutType(WorkoutType workoutType,  int numberOfExercises){
		List<Exercise> listOfExercisesForWorkoutType = exerciseService.getAllExercisesForWorkoutType(workoutType);
		Collections.shuffle(listOfExercisesForWorkoutType);
		return listOfExercisesForWorkoutType.subList(0, numberOfExercises);
	}

	private int getNumberOfReps(WorkoutGoal workoutGoal) {
		return switch (workoutGoal) {
			case STRENGTH -> 5;
			case ENDURANCE -> 20;
			case HYPERTROPHY -> 12;
		};
	}



	private int getRestAfterExerciseInSeconds(WorkoutGoal workoutGoal) {
		return switch (workoutGoal) {
			case STRENGTH -> 120;
			case ENDURANCE -> 20;
			case HYPERTROPHY -> 60;
		};
	}


}
