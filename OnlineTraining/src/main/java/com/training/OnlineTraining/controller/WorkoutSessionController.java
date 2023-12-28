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

		workoutSessionService.updateWorkoutSessions(workoutDTO.getWorkoutSessions());

		return "redirect:/workout/details/" + workoutID;
	}




}
