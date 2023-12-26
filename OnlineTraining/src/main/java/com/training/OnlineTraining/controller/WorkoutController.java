package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.service.WorkoutService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

	private final WorkoutService workoutService;

	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/create")
	public String showCreateWorkoutForm(Model model, HttpSession session) {
		model.addAttribute("workoutDTO", new WorkoutDTO()); // Provide an empty WorkoutDTO to the form
		return "workout/createWorkout"; // Assuming you have a view named "createWorkout"
	}

	@PostMapping("/create")
	public String createWorkout(@ModelAttribute("workoutDTO") WorkoutDTO workoutDTO, HttpSession session) {

		UUID contractID = (UUID) session.getAttribute("contractID");

		workoutDTO.setContractId(contractID);

		workoutService.createWorkout(workoutDTO);

		System.out.println("workoutDTO : " + workoutDTO);

		return "redirect:/workout"; // Redirect to the workout list after creating the workout
	}

	@GetMapping()
	public String getAllWorkoutsForContract(@RequestParam(value = "contractID", required = false) String passedContractID, HttpSession session, Model model) {
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
		Workout workout = workoutService.getWorkoutById(id);
		model.addAttribute("workout", workout);
		return "workout/workoutDetails"; // Assuming you have a view for displaying workout details
	}

	@GetMapping("/update/{id}")
	public String showUpdateWorkoutForm(@PathVariable UUID id, Model model) {
		Workout workout = workoutService.getWorkoutById(id);
		model.addAttribute("workout", workout);
		return "workout/updateWorkout"; // Assuming you have a view for updating workouts
	}

	@PostMapping("/update/{id}")
	public String updateWorkout(@PathVariable UUID id, @ModelAttribute("workout") WorkoutDTO workoutDTO, Model model) {
		workoutService.updateWorkout(id, workoutDTO);
		return "workout/updateWorkout"; // Assuming you have a view for updating workouts
	}

	@PostMapping("/delete/{id}")
	public String deleteWorkout(@PathVariable UUID id) {
		workoutService.deleteWorkout(id);
		return "redirect:/workout"; // Redirect to the workout list after deletion
	}
}
