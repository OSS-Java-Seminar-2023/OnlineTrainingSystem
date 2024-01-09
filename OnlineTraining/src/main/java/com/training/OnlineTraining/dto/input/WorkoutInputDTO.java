package com.training.OnlineTraining.dto.input;

import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.enums.WorkoutStatus;
import jakarta.persistence.Convert;
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
public class WorkoutInputDTO {
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


	private WorkoutStatus workoutStatus = WorkoutStatus.WAITING;

	private List<WorkoutSession> workoutSessions;

	public WorkoutInputDTO(UUID contractId) {
		this.contractId = contractId;
	}
}
