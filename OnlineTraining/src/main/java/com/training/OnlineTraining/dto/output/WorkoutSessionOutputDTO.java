package com.training.OnlineTraining.dto.output;

import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.additional.Duration;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WorkoutSessionOutputDTO {
	private UUID id;
	private UUID workoutId;
	private UUID exerciseId;
	private Integer numberOfReps;
	private Integer pauseAfterExerciseInSeconds;
	private BigDecimal weight;

	public static WorkoutSessionOutputDTO createEmptyWorkoutSessionDTO() {
		WorkoutSessionOutputDTO emptyWorkoutSession = new WorkoutSessionOutputDTO();
		emptyWorkoutSession.setWorkoutId(null);
		emptyWorkoutSession.setExerciseId(null);
		emptyWorkoutSession.setNumberOfReps(0);
		emptyWorkoutSession.setPauseAfterExerciseInSeconds(0);
		emptyWorkoutSession.setWeight(BigDecimal.ZERO);
		return emptyWorkoutSession;
	}

}
