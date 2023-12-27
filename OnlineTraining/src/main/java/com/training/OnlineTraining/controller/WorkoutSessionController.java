package com.training.OnlineTraining.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.mapper.WorkoutSessionMapper;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.service.WorkoutSessionService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workoutSession")
public class WorkoutSessionController {

	private final WorkoutService workoutService;
	private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionController.class);


	public WorkoutSessionController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@PostMapping("/update/{workoutID}")
	public String updateWorkoutAndSessions(
			@PathVariable UUID workoutID,
			@ModelAttribute("workout") WorkoutDTO workoutDTO,
			HttpSession session) {

		// Retrieve the existing workout
		Workout existingWorkout = workoutService.getWorkoutById(workoutID);

		existingWorkout.setWorkoutSessions(workoutDTO.getWorkoutSessions());

		Workout returnedWorkout = workoutService.updateWorkout(existingWorkout);

		// Redirect to the workout details page or any other page after updating
		return "redirect:/workout/details/" + workoutID;
	}




}
