package com.training.OnlineTraining.controller;

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

	// Existing method to get all workouts for a contract
	@GetMapping()
	public String getAllWorkoutsForContract(@RequestParam(value = "contractID", required = false) String passedContractID, HttpSession session, Model model) {

		UUID  contractID = null;

		if(session.getAttribute("contractID") != null){
			contractID = (UUID) session.getAttribute("contractID");
		}
		else{
			contractID = UUID.fromString(passedContractID);
			session.setAttribute("contractID", contractID);
		}

		List<Workout> workouts = workoutService.getWorkoutsByContractID(contractID);

		model.addAttribute("workoutsList", workouts);

		return "workout/workoutList";
	}
}
