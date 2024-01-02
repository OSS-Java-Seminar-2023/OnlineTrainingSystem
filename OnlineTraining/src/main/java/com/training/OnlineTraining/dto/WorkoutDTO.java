package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.converter.ExerciseEquipmentConverter;
import com.training.OnlineTraining.converter.WorkoutStatusConverter;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.enums.WorkoutStatus;
import jakarta.persistence.Convert;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
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
	private Boolean isFinished;

	@Convert(converter = WorkoutStatusConverter.class)
	private WorkoutStatus workoutStatus;

	private List<WorkoutSession> workoutSessions;

	public WorkoutDTO(UUID contractId) {
		this.contractId = contractId;
	}
}
