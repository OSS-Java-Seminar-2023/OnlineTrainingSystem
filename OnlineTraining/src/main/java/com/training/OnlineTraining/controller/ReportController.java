package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.service.PdfGenerator;
import com.training.OnlineTraining.service.WorkoutService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/report")
public class ReportController {

	private final WorkoutService workoutService;

	public ReportController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/show")
	public String showPdf(@RequestParam UUID id, Model model) {

		WorkoutOutputDTO workoutOutputDTO = workoutService.getWorkoutById(id);

		model.addAttribute("workout", workoutOutputDTO);

		return "report/workout_pdf_template";
	}

	@PostMapping("/generate")
	public void generatePdf(@ModelAttribute WorkoutOutputDTO workout, HttpServletResponse response) throws IOException {
		// Convert WorkoutOutputDTO object to PDF and allow download
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=generated-pdf.pdf");

		PdfGenerator.generatePdfFromHtml(workout, response.getOutputStream());
	}

}