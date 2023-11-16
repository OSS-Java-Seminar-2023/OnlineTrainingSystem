package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.WorkoutSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutSessionService {
    private final WorkoutSessionRepository workoutSessionRepository;
    @Autowired
    public WorkoutSessionService(WorkoutSessionRepository workoutSessionRepository){
        this.workoutSessionRepository = workoutSessionRepository;
    }

}
