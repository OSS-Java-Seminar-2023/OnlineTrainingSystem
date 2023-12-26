package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.mapper.ExerciseMapper;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        logger.info("ExerciseController initialized.");
    }

    @GetMapping("/create")
    public String showAddExerciseForm(Model model) {
        logger.info("Displaying add exercise form.");
        model.addAttribute("exercise", new ExerciseDTO());
        return "exercise/exerciseForm";
    }

    @PostMapping("/create")
    public String createExercise(@ModelAttribute ExerciseDTO exerciseDTO, Model model) {
        logger.info("Creating a new exercise.");
        Exercise createdExercise = exerciseService.createExercise(exerciseDTO);
        return "redirect:/exercise";
    }

    @GetMapping("/details/{id}")
    public String showExerciseDetails(@PathVariable UUID id, Model model) {
        logger.info("Displaying exercise details for ID: {}", id);
        Exercise exercise = exerciseService.getExerciseById(id);
        model.addAttribute("exercise", exercise);
        return "exercise/exerciseDetails";
    }

    @GetMapping()
    public String getAllExercises(Model model) {
        logger.info("Fetching all exercises.");
        List<Exercise> exercises = exerciseService.getAllExercises();
        model.addAttribute("exercises", exercises);
        return "exercise/exerciseList";
    }

    @GetMapping("/update/{id}")
    public String getUpdateFormForExercise(@PathVariable UUID id, Model model) {
        logger.info("Displaying update form for exercise ID: {}", id);
        Exercise tempExercise = exerciseService.getExerciseById(id);
        model.addAttribute("exerciseForUpdating", tempExercise);
        return "exercise/exerciseUpdateForm";
    }

    @PostMapping("/update/{id}")
    public String updateExercise(@PathVariable String id, @ModelAttribute("exercise") ExerciseDTO exerciseDTO, Model model) {
        logger.info("Updating exercise for ID: {}", id);
        logger.info("ExerciseDTO: {}", exerciseDTO);
        exerciseService.updateExercise(exerciseDTO.getId(), exerciseDTO);
        return "redirect:/exercise";
    }

    @PostMapping("/delete/{id}")
    public String deleteExercise(@PathVariable UUID id) {
        logger.info("Deleting exercise for ID: {}", id);
        exerciseService.deleteExercise(id);
        return "redirect:/exercise";
    }
}
