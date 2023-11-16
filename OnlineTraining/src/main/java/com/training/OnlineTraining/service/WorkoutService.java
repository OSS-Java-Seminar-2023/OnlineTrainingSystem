package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository){
        this.workoutRepository = workoutRepository;
    }

}
