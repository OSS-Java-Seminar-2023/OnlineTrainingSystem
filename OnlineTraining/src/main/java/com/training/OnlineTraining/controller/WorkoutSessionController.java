package com.training.OnlineTraining.controller;

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

	@GetMapping("/create")
	public String showCreateWorkoutSessionForm(Model model) {
		logger.info("Displaying create workout session form.");

		model.addAttribute("workoutSessionDTO", new WorkoutSessionDTO());
		return "workoutSession/createWorkoutSession";
	}

	@PostMapping("/create")
	public String createWorkoutSession(@ModelAttribute("workoutSessionDTO") WorkoutSessionDTO workoutSessionDTO) {
		logger.info("Creating a new workout session.");

		workoutSessionService.createWorkoutSession(workoutSessionDTO);
		return "redirect:/workoutSession";
	}

	@GetMapping()
	public String getAllWorkoutSessions(Model model) {
		logger.info("Fetching all workout sessions.");

		List<WorkoutSession> workoutSessions = workoutSessionService.getAllWorkoutSessions();
		model.addAttribute("workoutSessions", workoutSessions);
		return "workoutSession/workoutSessionList";
	}

	@GetMapping("/details/{id}")
	public String showWorkoutSessionDetails(@PathVariable UUID id, Model model) {
		logger.info("Displaying workout session details for ID: {}", id);

		WorkoutSession workoutSession = workoutSessionService.getWorkoutSessionById(id);
		model.addAttribute("workoutSession", workoutSession);
		return "workoutSession/workoutSessionDetails";
	}

	@GetMapping("/update/{id}")
	public String showUpdateWorkoutSessionForm(@PathVariable UUID id, Model model) {
		logger.info("Displaying update workout session form for ID: {}", id);

		WorkoutSession workoutSession = workoutSessionService.getWorkoutSessionById(id);
		model.addAttribute("workoutSessionDTO", workoutSession);
		return "workoutSession/updateWorkoutSession";
	}

	@PostMapping("/update/{id}")
	public String updateWorkoutSession(@PathVariable UUID id, @ModelAttribute("workoutSessionDTO") WorkoutSessionDTO workoutSessionDTO) {
		logger.info("Updating workout session for ID: {}", id);

		workoutSessionService.updateWorkoutSession(id, workoutSessionDTO);
		return "redirect:/workoutSession";
	}

	@PostMapping("/delete/{id}")
	public String deleteWorkoutSession(@PathVariable UUID id) {
		logger.info("Deleting workout session for ID: {}", id);

		workoutSessionService.deleteWorkoutSession(id);
		return "redirect:/workoutSession";
	}
}
