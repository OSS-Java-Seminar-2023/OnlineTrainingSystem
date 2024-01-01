package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.model.additional.Duration;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkoutSessionService {
    WorkoutSession createWorkoutSession(WorkoutSessionDTO workoutSessionDTO);
    WorkoutSession getWorkoutSessionById(UUID id);
    List<WorkoutSession> getAllWorkoutSessions();
    List<WorkoutSession> getAllByWorkoutId(UUID workoutID);
    WorkoutSession updateWorkoutSession(UUID id, WorkoutSessionDTO workoutSessionDetails);

    void updateWorkoutSessions(List<WorkoutSession> workoutSessionList);
    void deleteWorkoutSession(UUID id);
    void deleteAllWorkoutSessions();
    public Optional<Exercise> getExerciseById(UUID workoutSessionId);
}
