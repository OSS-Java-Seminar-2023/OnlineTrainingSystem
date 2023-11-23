package com.training.OnlineTraining.mapper;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.model.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "equipmentNeeded", target = "equipmentNeeded")
    @Mapping(source = "difficultyLevel", target = "difficultyLevel")
    Exercise toExercise(ExerciseDTO exerciseDTO);
}