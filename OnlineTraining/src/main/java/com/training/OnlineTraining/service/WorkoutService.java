package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.repository.WorkoutRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface WorkoutService {
	List<Workout> getWorkoutsByContractID(UUID contractID);

}
