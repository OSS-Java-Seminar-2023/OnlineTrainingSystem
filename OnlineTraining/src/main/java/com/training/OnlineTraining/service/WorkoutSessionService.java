package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.WorkoutSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;

}
