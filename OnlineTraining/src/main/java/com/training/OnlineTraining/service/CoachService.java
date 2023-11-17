package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

}
