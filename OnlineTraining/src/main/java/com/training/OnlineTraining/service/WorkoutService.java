package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.WorkoutRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutService {
    private final WorkoutRepository workoutRepository;


}
