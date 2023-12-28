package com.training.OnlineTraining.controller;


import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.service.WorkoutSessionService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/workoutSession")
public class WorkoutSessionController {

	private final WorkoutSessionService workoutSessionService;

	private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionController.class);

	public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
		this.workoutSessionService = workoutSessionService;
	}

	@PostMapping("/update/{workoutID}")
	public String updateWorkoutAndSessions(
			@PathVariable UUID workoutID,
			@ModelAttribute("workout") WorkoutDTO workoutDTO,
			HttpSession session) {

		logger.info("Updating workout and sessions for workout ID: {}", workoutID);

		workoutSessionService.updateWorkoutSessions(workoutDTO.getWorkoutSessions());

		logger.info("Workout and sessions updated successfully.");

		return "redirect:/workout/details/" + workoutID;
	}

	@PostMapping("/delete/{workoutSessionID}/{workoutID}")
	public String deleteWorkoutSession(@PathVariable String workoutSessionID, @PathVariable String workoutID) {
		logger.info("Deleting workout session with ID: {}", workoutSessionID);

		workoutSessionService.deleteWorkoutSession(UUID.fromString(workoutSessionID));

		logger.info("Workout session deleted successfully.");

		// Redirect to the appropriate page after deletion
		return "redirect:/workout/details/" + UUID.fromString(workoutID); // Assuming the path is correct
	}


}

