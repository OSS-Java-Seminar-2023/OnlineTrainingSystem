package com.training.OnlineTraining.mapper;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.dto.CoachUserDTO;
import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = CoachUserMapper.class)
public interface CoachMapper {

	CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

	@Mapping(target = "coachUserDTO", source = "user")
	@Mapping(source = "yearsOfExperience", target = "yearsOfExperience")
	@Mapping(source = "education", target = "education")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "monthlyPrice", target = "monthlyPrice")
	CoachDto coachToCoachDto(Coach coach);

	@Mapping(target = "user", source = "coachUserDTO")
	@Mapping(source = "yearsOfExperience", target = "yearsOfExperience")
	@Mapping(source = "education", target = "education")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "monthlyPrice", target = "monthlyPrice")
	Coach coachDtoToCoach(CoachDto coachDto);
}