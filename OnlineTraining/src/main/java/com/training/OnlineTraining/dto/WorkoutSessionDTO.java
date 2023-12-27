package com.training.OnlineTraining.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WorkoutSessionDTO {
	private UUID id;
	private UUID workoutId;
	private UUID exerciseId;
	private Integer numberOfReps;
	private Integer pauseAfterExerciseInSeconds;
	private BigDecimal weight;

	public static WorkoutSessionDTO createEmptyWorkoutSessionDTO() {
		WorkoutSessionDTO emptyWorkoutSession = new WorkoutSessionDTO();
		emptyWorkoutSession.setWorkoutId(null);
		emptyWorkoutSession.setExerciseId(null);
		emptyWorkoutSession.setNumberOfReps(0);
		emptyWorkoutSession.setPauseAfterExerciseInSeconds(0);
		emptyWorkoutSession.setWeight(BigDecimal.ZERO);
		return emptyWorkoutSession;
	}

}
