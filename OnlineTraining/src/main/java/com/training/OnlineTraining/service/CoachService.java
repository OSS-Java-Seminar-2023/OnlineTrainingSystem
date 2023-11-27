package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.model.User;

import java.util.UUID;


public interface CoachService {

   void registerCoach(CoachDto coachDto, UUID userId);
   boolean isCoach(User user);
}
