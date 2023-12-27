package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.service.WorkoutSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workoutSession")
public class WorkoutSessionController {

	private final WorkoutSessionService workoutSessionService;
	private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionController.class);

	public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
		this.workoutSessionService = workoutSessionService;

		logger.info("WorkoutSessionController initialized.");
	}


	@PostMapping("/update/{id}")
	public String updateWorkoutDetails(@PathVariable UUID id,
	                                   @ModelAttribute("workout") WorkoutDTO updatedWorkout,
	                                   @ModelAttribute("workoutSessionDTOList") List<WorkoutSessionDTO> updatedSessions) {
		logger.info("Updating workout details for ID: {}", id);

		// Update the workout using updatedWorkout (if needed)

		// Update or save the modified workout sessions
		for (WorkoutSessionDTO session : updatedSessions) {
			// Check if session needs to be updated or saved (handle accordingly)
			// Update or save each modified session using session data (exerciseId, numberOfReps, etc.)
		}

		// Redirect to a confirmation page or any other appropriate page
		return "redirect:/details/" + id;
	}

}
