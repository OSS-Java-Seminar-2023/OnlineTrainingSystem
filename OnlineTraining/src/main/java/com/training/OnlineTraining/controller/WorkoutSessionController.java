package com.training.OnlineTraining.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.service.WorkoutSessionService;
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

	private final WorkoutSessionService workoutSessionService;
	private static final Logger logger = LoggerFactory.getLogger(WorkoutSessionController.class);

	public WorkoutSessionController(WorkoutSessionService workoutSessionService) {
		this.workoutSessionService = workoutSessionService;

		logger.info("WorkoutSessionController initialized.");
	}


	@PostMapping("/update/{workoutID}")
	public String updateWorkoutDetails(
			@PathVariable UUID workoutID,
			@ModelAttribute("workout") WorkoutDTO updatedWorkout,
			@RequestBody ArrayList<WorkoutSessionDTO> workoutSessionDTOList,
			Model model) {

		System.out.println("List size : " + workoutSessionDTOList.size());

		return "";
	}



}
