package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository){
        this.exerciseRepository = exerciseRepository;
    }
}
