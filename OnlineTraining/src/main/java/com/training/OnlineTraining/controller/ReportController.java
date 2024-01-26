package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.service.ReportGenerator;
import com.training.OnlineTraining.service.WorkoutService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/report")
public class ReportController {

	private static final Logger logger = Logger.getLogger(ReportController.class.getName());

	private final WorkoutService workoutService;

	public ReportController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/show")
	public String showPdf(@RequestParam UUID id, Model model) {
		logger.info("Request to show PDF for workout with ID: " + id);

		WorkoutOutputDTO workoutOutputDTO = workoutService.getWorkoutById(id);

		model.addAttribute("workout", workoutOutputDTO);

		return "report/workout_pdf_template";
	}

	@GetMapping("/generate")
	public void generatePdf(@RequestParam UUID id, HttpServletResponse response) throws IOException {
		logger.info("Request to generate PDF for workout with ID: " + id);

		WorkoutOutputDTO workout = workoutService.getWorkoutById(id);

		// Convert WorkoutOutputDTO object to PDF and allow download
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=generated-pdf.pdf");

		ReportGenerator.generateReportFromHtml(workout, response.getOutputStream());

		logger.info("PDF generation completed for workout with ID: " + id);
	}
}
