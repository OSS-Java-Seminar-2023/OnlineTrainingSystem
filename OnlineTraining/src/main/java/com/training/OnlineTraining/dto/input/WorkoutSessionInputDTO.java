package com.training.OnlineTraining.dto.input;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WorkoutSessionInputDTO {
	private UUID workoutId;
	private UUID exerciseId;
	private Integer numberOfReps;
	private Integer pauseAfterExerciseInSeconds;
	private BigDecimal weight;

	public static WorkoutSessionInputDTO createEmptyWorkoutSessionDTO() {
		WorkoutSessionInputDTO emptyWorkoutSession = new WorkoutSessionInputDTO();
		emptyWorkoutSession.setWorkoutId(null);
		emptyWorkoutSession.setExerciseId(null);
		emptyWorkoutSession.setNumberOfReps(0);
		emptyWorkoutSession.setPauseAfterExerciseInSeconds(0);
		emptyWorkoutSession.setWeight(BigDecimal.ZERO);
		return emptyWorkoutSession;
	}

}
