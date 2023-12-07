package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/add")
    public String showAddExerciseForm(Model model) {
        model.addAttribute("exerciseDTO", new ExerciseDTO()); // Create a blank ExerciseDTO to bind the form
        return "addExerciseForm"; // Return the Thymeleaf view name for the add exercise form
    }

    @PostMapping("/create")
    public String createExercise(@ModelAttribute ExerciseDTO exerciseDTO, Model model) {
        Exercise createdExercise = exerciseService.createExercise(exerciseDTO);
        return ""; // Return the Thymeleaf view name
    }

    @GetMapping("/show/{id}")
    public String getExerciseById(@PathVariable UUID id, Model model) {
        Exercise exercise = exerciseService.getExerciseById(id).orElse(null);
        model.addAttribute("exercise", exercise);
        return "exerciseDetails"; // Return the Thymeleaf view name
    }

    @GetMapping("/all")
    public String getAllExercises(Model model) {
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "allExercises"; // Return the Thymeleaf view name
    }

    @PutMapping("/update/{id}")
    public String updateExercise(@PathVariable UUID id, @ModelAttribute ExerciseDTO exerciseDTO, Model model) {
        Exercise updatedExercise = exerciseService.updateExercise(id, exerciseDTO);
        model.addAttribute("exercise", updatedExercise);
        return "exerciseView"; // Return the Thymeleaf view name
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteExercise(id);
        return "redirect:/exercises/all"; // Redirect to the endpoint displaying all exercises
    }
}
