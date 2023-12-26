package com.training.OnlineTraining.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class WorkoutSessionDTO {
	private UUID workoutId;
	private UUID exerciseId;
	private Integer numberOfReps;
	private Integer pauseAfterExerciseInSeconds;
	private BigDecimal weight;

	// Constructors, getters, setters, etc. as needed
}
