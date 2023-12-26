package com.training.OnlineTraining.mapper;

import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.WorkoutSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface WorkoutSessionMapper {
	WorkoutSessionMapper INSTANCE = Mappers.getMapper(WorkoutSessionMapper.class);

	@Mapping(source = "workout.id", target = "workoutId")
	@Mapping(source = "exercise.id", target = "exerciseId")
	WorkoutSessionDTO toWorkoutSessionDTO(WorkoutSession workoutSession);

	@Mapping(source = "workoutId", target = "workout.id")
	@Mapping(source = "exerciseId", target = "exercise.id")
	WorkoutSession toWorkoutSession(WorkoutSessionDTO workoutSessionDTO);
}
