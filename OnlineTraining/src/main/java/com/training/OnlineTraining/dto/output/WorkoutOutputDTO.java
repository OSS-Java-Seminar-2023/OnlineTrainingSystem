package com.training.OnlineTraining.dto.output;

import com.training.OnlineTraining.converter.WorkoutStatusConverter;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.additional.Duration;
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
public class WorkoutOutputDTO {
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
	private WorkoutStatus workoutStatus = WorkoutStatus.WAITING;

	private List<WorkoutSession> workoutSessions;

	public WorkoutOutputDTO(UUID contractId) {
		this.contractId = contractId;
	}

	public Duration getDuration() {
		Duration newDuration = new Duration();

		newDuration.add(warmingUpTimeInSeconds);
		newDuration.add((numberOfSets - 1) * pauseBetweenSetsInSeconds);

		for(WorkoutSession workoutSession : workoutSessions)
			newDuration.add(workoutSession.getDuration().getDurationInSeconds() * numberOfSets);

		return newDuration;
	}
}
