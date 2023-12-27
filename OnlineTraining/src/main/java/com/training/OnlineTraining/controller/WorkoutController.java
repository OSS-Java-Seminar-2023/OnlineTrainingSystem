package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.service.ExerciseService;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.service.WorkoutSessionService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

	private final WorkoutService workoutService;
	private final ExerciseService exerciseService;
	private final WorkoutSessionService workoutSessionService;
	private static final Logger logger = LoggerFactory.getLogger(WorkoutController.class);

	public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, WorkoutSessionService workoutSessionService) {
		logger.info("WorkoutController initialized.");

		this.workoutService = workoutService;
		this.exerciseService = exerciseService;
		this.workoutSessionService = workoutSessionService;
	}

	@GetMapping("/create")
	public String showCreateWorkoutForm(Model model) {
		logger.info("Displaying create workout form.");

		model.addAttribute("workoutDTO", new WorkoutDTO());

		return "workout/createWorkout";
	}

	@PostMapping("/create")
	public String createWorkout(@ModelAttribute("workoutDTO") WorkoutDTO workoutDTO, HttpSession session) {
		logger.info("Creating a new workout.");

		UUID contractID = (UUID) session.getAttribute("contractID");

		workoutService.createWorkout(workoutDTO, contractID);

		return "redirect:/workout";
	}

	@GetMapping()
	public String getAllWorkoutsForContract(@RequestParam(value = "contractID", required = false) String passedContractID, HttpSession session, Model model) {
		logger.info("Fetching all workouts for a contract.");

		UUID contractID = (UUID) session.getAttribute("contractID");

		if (contractID == null) {
			contractID = (passedContractID != null) ? UUID.fromString(passedContractID) : null;
			session.setAttribute("contractID", contractID);
		}

		List<Workout> workouts = workoutService.getWorkoutsByContractID(contractID);
		model.addAttribute("workoutsList", workouts);

		return "workout/workoutList";
	}

	@GetMapping("/details/{id}")
	public String showWorkoutDetails(@PathVariable UUID id, Model model) {
		logger.info("Displaying workout details for ID: {}", id);

		Workout workout = workoutService.getWorkoutById(id);
		model.addAttribute("workout", workout);
		model.addAttribute("listExercises", exerciseService.getAllExercises());
		model.addAttribute("workoutSessionList", workoutSessionService.getAllByWorkoutId(id));

		return "workout/workoutDetails";
	}

	@GetMapping("/update/{id}")
	public String showUpdateWorkoutForm(@PathVariable UUID id, Model model) {
		logger.info("Displaying update workout form for ID: {}", id);

		Workout workout = workoutService.getWorkoutById(id);
		model.addAttribute("workout", workout);

		return "workout/updateWorkout";
	}

	@PostMapping("/update/{id}")
	public String updateWorkout(@PathVariable UUID id, @ModelAttribute("workout") WorkoutDTO workoutDTO, HttpSession session) {
		logger.info("Updating workout for ID: {}", id);

		UUID contractID = (UUID) session.getAttribute("contractID");

		workoutService.updateWorkout(id, workoutDTO, contractID);

		return "redirect:/workout";
	}

	@PostMapping("/delete/{id}")
	public String deleteWorkout(@PathVariable UUID id) {
		logger.info("Deleting workout for ID: {}", id);

		workoutService.deleteWorkout(id);

		return "redirect:/workout";
	}
}
