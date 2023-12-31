package com.training.OnlineTraining.utils;

import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.input.WorkoutSessionInputDTO;
import com.training.OnlineTraining.model.enums.WorkoutStatus;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class TestDTOUtils {

	public static WorkoutInputDTO getWorkoutDTO(UUID contractID){
		WorkoutInputDTO workoutInputDTO = new WorkoutInputDTO();

		workoutInputDTO.setDateOfWorkout(DateUtils.getTodayDate());
		workoutInputDTO.setContractId(contractID);

		workoutInputDTO.setOrdinalNumberOfWorkout(getRandomNumber());
		workoutInputDTO.setNumberOfExercises(getRandomNumber());
		workoutInputDTO.setWarmingUpTimeInSeconds(getRandomNumber());
		workoutInputDTO.setNumberOfSets(getRandomNumber());
		workoutInputDTO.setPauseBetweenSetsInSeconds(getRandomNumber());
		workoutInputDTO.setSelfRating(getRandomNumber());
		workoutInputDTO.setWorkoutStatus(WorkoutStatus.WAITING);

		return workoutInputDTO;
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

