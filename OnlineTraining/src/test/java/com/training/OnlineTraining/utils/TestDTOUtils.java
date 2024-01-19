package com.training.OnlineTraining.utils;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.input.WorkoutSessionInputDTO;
import com.training.OnlineTraining.model.enums.WorkoutStatus;
import com.training.OnlineTraining.util.PasswordUtils;

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
		WorkoutSessionInputDTO workoutSessionInputDTO = new WorkoutSessionInputDTO(workoutId);

		workoutSessionInputDTO.setWorkoutId(workoutId);
		workoutSessionInputDTO.setExerciseId(exerciseId);

		workoutSessionInputDTO.setNumberOfReps(getRandomNumber());
		workoutSessionInputDTO.setPauseAfterExerciseInSeconds(getRandomNumber());
		workoutSessionInputDTO.setWeight(BigDecimal.valueOf(getRandomDecimalNumber()));

		return workoutSessionInputDTO;
	}


	public static MeasurementDTO getMeasurementDTO(UUID contractId) {
		MeasurementDTO measurementDTO = new MeasurementDTO();
		measurementDTO.setContractId(contractId);
		measurementDTO.setMeasurementDate(DateUtils.getTodayDate());
		measurementDTO.setBodyWeight(getRandomDecimalNumber());
		measurementDTO.setBodyFat(getRandomDecimalNumber());
		measurementDTO.setWaistCircumference(getRandomDecimalNumber());
		measurementDTO.setChestCircumference(getRandomDecimalNumber());
		measurementDTO.setArmCircumference(getRandomDecimalNumber());
		measurementDTO.setLegCircumference(getRandomDecimalNumber());
		return measurementDTO;
	}

	public static UserDto getUserDTO() {
		UserDto userDto = new UserDto();

		int randomNumber = getRandomNumber();

		userDto.setFirstName("FirstName" + randomNumber);
		userDto.setLastName("LastName" + randomNumber);
		userDto.setEmail("Email" + randomNumber);
		userDto.setStreet("Street" + randomNumber);
		userDto.setCity("City" + randomNumber);
		userDto.setCountry("Country" + randomNumber);
		userDto.setPhoneNumber("PhoneNumber" +randomNumber);
		userDto.setGender("Gender" + randomNumber);
		userDto.setPassword("Password " + randomNumber);


		return userDto;
	}

	private static int getRandomNumber(){
		return new Random().nextInt(1000);
	}

	private static double getRandomDecimalNumber(){
		return new Random().nextDouble(1000.0);
	}
}

