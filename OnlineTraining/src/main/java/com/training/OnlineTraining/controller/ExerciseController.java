package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.mapper.ExerciseMapper;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    private final ExerciseMapper exerciseMapper;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }

    @GetMapping("/create")
    public String showAddExerciseForm(Model model) {
        model.addAttribute("exercise", new ExerciseDTO());
        return "exercise/exerciseForm";
    }

    @PostMapping("/create")
    public String createExercise(@ModelAttribute ExerciseDTO exerciseDTO, Model model) {
        Exercise createdExercise = exerciseService.createExercise(exerciseDTO);
        return "redirect:/exercise";
    }

    @GetMapping("/details/{id}")
    public String showExerciseDetails(@PathVariable UUID id, Model model) {
        Exercise exercise = exerciseService.getExerciseById(id);
        model.addAttribute("exercise", exercise);
        return "exercise/exerciseDetails"; // Create a details view
    }


    @GetMapping()
    public String getAllExercises(Model model) {
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercise/exerciseList"; // Return the Thymeleaf view name
    }

    @GetMapping("/update/{id}")
    public String getUpdateFormForExercise(@PathVariable UUID id, Model model) {
        Exercise tempExercise = exerciseService.getExerciseById(id);
        System.out.println("getUpdateFormForExercise ->  exercise : " + tempExercise);
        model.addAttribute("exerciseForUpdating", tempExercise);
        return "exercise/exerciseUpdateForm"; // Return the Thymeleaf view name
    }

    @PostMapping("/update/{id}")
    public String updateExercise(@PathVariable String id, @ModelAttribute("exercise") ExerciseDTO exerciseDTO, Model model) {
        System.out.println("updateExercise -> " + id);
        System.out.println("updateExercise -> " + exerciseDTO);
        exerciseService.updateExercise(exerciseDTO.getId(), exerciseDTO);
        return "redirect:/exercise";
    }

    @PostMapping("/delete/{id}")
    public String deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteExercise(id);
        return "redirect:/exercise";
    }
}
