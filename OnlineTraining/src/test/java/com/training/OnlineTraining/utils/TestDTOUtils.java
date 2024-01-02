package com.training.OnlineTraining.utils;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.input.WorkoutSessionInputDTO;
import com.training.OnlineTraining.model.enums.WorkoutStatus;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class TestDTOUtils {

	public static WorkoutDTO getWorkoutDTO(UUID contractID){
		WorkoutDTO workoutDTO = new WorkoutDTO();

		workoutDTO.setDateOfWorkout(DateUtils.getTodayDate());
		workoutDTO.setContractId(contractID);

		workoutDTO.setOrdinalNumberOfWorkout(getRandomNumber());
		workoutDTO.setNumberOfExercises(getRandomNumber());
		workoutDTO.setWarmingUpTimeInSeconds(getRandomNumber());
		workoutDTO.setNumberOfSets(getRandomNumber());
		workoutDTO.setPauseBetweenSetsInSeconds(getRandomNumber());
		workoutDTO.setSelfRating(getRandomNumber());
		workoutDTO.setWorkoutStatus(WorkoutStatus.WAITING);

		return workoutDTO;
	}

	public static WorkoutSessionInputDTO getWorkoutSessionDTO(UUID workoutId, UUID exerciseId){
		WorkoutSessionInputDTO workoutSessionInputDTO = new WorkoutSessionInputDTO();

		workoutSessionInputDTO.setWorkoutId(workoutId);
		workoutSessionInputDTO.setExerciseId(exerciseId);

		workoutSessionInputDTO.setNumberOfReps(getRandomNumber());
		workoutSessionInputDTO.setPauseAfterExerciseInSeconds(getRandomNumber());
		workoutSessionInputDTO.setWeight(BigDecimal.valueOf(getRandomDecimalNumber()));

		return workoutSessionInputDTO;
	}

	private static int getRandomNumber(){
		return new Random().nextInt(1000);
	}

	private static double getRandomDecimalNumber(){
		return new Random().nextDouble(1000.0);
	}
}

