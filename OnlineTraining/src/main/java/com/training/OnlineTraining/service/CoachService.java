package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.User;

import java.util.List;
import java.util.UUID;


public interface CoachService {

   void registerCoach(CoachDto coachDto, UUID userId);
   boolean isCoach(User user);
   List<CoachDto> getAllCoaches();
   List<CoachDto> filterCoaches(String gender, Double experience, Integer age, String education, Double monthlyPrice);
   CoachDto mapCoachToDto(Coach coach);
}
