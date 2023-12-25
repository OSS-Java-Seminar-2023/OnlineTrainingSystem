package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.service.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/workout")
public class WorkoutController {

	private final WorkoutService workoutService;

	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping()
	public String getAllWorkoutsForContract(@RequestParam("contractID") String contractID, Model model) {

		List<Workout> workouts = workoutService.getWorkoutsByContractID(UUID.fromString(contractID));

		model.addAttribute("workoutsList", workouts);

		return "workout/workoutList";
	}

}
