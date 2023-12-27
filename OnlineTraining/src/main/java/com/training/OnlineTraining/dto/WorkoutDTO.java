package com.training.OnlineTraining.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkoutDTO {
	private UUID id;
	private UUID contractId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfWorkout;

	private Integer ordinalNumberOfWorkout;
	private Integer numberOfExercises;
	private Integer warmingUpTimeInSeconds;
	private Integer numberOfSets;
	private Integer pauseBetweenSetsInSeconds;
	private Integer selfRating;

	public WorkoutDTO(UUID contractId) {
		this.contractId = contractId;
	}
}
