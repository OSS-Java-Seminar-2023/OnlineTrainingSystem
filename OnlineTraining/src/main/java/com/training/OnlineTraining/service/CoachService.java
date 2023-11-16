package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoachService {

    private final CoachRepository coachRepository;
    @Autowired
    public CoachService(CoachRepository coachRepository){
        this.coachRepository = coachRepository;
    }
}
