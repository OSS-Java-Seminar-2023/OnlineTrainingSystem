package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.model.enums.WorkoutStatus;
import com.training.OnlineTraining.service.ExerciseService;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.service.WorkoutSessionService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

		model.addAttribute("workoutInputDTO", new WorkoutInputDTO());

		return "workout/createWorkout";
	}

	@PostMapping("/create")
	public String createWorkout(@ModelAttribute("workoutDTO") WorkoutInputDTO workoutInputDTO, HttpSession session) {
		logger.info("Creating a new workout.");

		UUID contractID = (UUID) session.getAttribute("contractID");

		workoutService.createWorkout(workoutInputDTO, contractID);

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

		List<WorkoutOutputDTO> workouts = workoutService.getWorkoutsByContractID(contractID);
		model.addAttribute("workoutsList", workouts);

		UUID clientId = (UUID) session.getAttribute("clientId");
		if (clientId == null){

			return "workout/workoutList";
		}
		return "workout/workoutClientList";
	}

	@GetMapping("/details/{id}")
	public String showWorkoutDetails(@PathVariable UUID id, Model model, HttpSession session) {
		logger.info("Displaying workout details for ID: {}", id);

		WorkoutOutputDTO workout = workoutService.getWorkoutById(id);

		model.addAttribute("workout", workout);
		model.addAttribute("listExercises", exerciseService.getAllExercises());

		UUID clientId = (UUID) session.getAttribute("clientId");
		if (clientId == null){

			return "workout/workoutDetails";
		}

		return "workout/workoutClientDetails";
	}

	@GetMapping("/update/{id}")
	public String showUpdateWorkoutForm(@PathVariable UUID id, Model model, HttpSession session) {
		logger.info("Displaying update workout form for ID: {}", id);

		List<WorkoutStatus> workoutStatuses = Arrays.asList(WorkoutStatus.values());
		model.addAttribute("workoutStatuses", workoutStatuses);

		WorkoutOutputDTO workout = workoutService.getWorkoutById(id);
		model.addAttribute("workout", workout);

		UUID clientId = (UUID) session.getAttribute("clientId");
		if (clientId == null){

			return "workout/updateWorkout";
		}
		return "workout/updateClientWorkout";


	}

	@PostMapping("/update/{id}")
	public String updateWorkout(@PathVariable UUID id, @ModelAttribute("workout") WorkoutInputDTO workoutInputDTO, HttpSession session) {
		logger.info("Updating workout for ID: {}", id);

		UUID contractID = (UUID) session.getAttribute("contractID");

		workoutService.updateWorkout(id, workoutInputDTO, contractID);


		return "redirect:/workout";
	}

	@PostMapping("/delete/{id}")
	public String deleteWorkout(@PathVariable UUID id) {
		logger.info("Deleting workout for ID: {}", id);

		workoutService.deleteWorkout(id);

		return "redirect:/workout";
	}
}
