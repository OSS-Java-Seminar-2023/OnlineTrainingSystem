package com.training.OnlineTraining.utils;

import com.training.OnlineTraining.dto.WorkoutDTO;

import java.util.Date;
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

		return workoutDTO;
	}

	private static int getRandomNumber(){
		return new Random().nextInt(1000);
	}
}

