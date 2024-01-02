package com.training.OnlineTraining.utils;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.enums.WorkoutStatus;
import org.springframework.boot.context.properties.bind.BindHandler;

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

	public static WorkoutSessionDTO getWorkoutSessionDTO(UUID workoutId, UUID exerciseId){
		WorkoutSessionDTO workoutSessionDTO = new WorkoutSessionDTO();

		workoutSessionDTO.setWorkoutId(workoutId);
		workoutSessionDTO.setExerciseId(exerciseId);

		workoutSessionDTO.setNumberOfReps(getRandomNumber());
		workoutSessionDTO.setPauseAfterExerciseInSeconds(getRandomNumber());
		workoutSessionDTO.setWeight(BigDecimal.valueOf(getRandomDecimalNumber()));

		return workoutSessionDTO;
	}

	private static int getRandomNumber(){
		return new Random().nextInt(1000);
	}

	private static double getRandomDecimalNumber(){
		return new Random().nextDouble(1000.0);
	}
}

