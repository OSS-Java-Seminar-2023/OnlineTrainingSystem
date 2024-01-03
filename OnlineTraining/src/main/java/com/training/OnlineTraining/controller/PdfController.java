package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.service.PdfGenerator;
import com.training.OnlineTraining.service.WorkoutService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class PdfController {

	private final WorkoutService workoutService;

	public PdfController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	@GetMapping("/show-pdf")
	public String showPdf(Model model) {

		List<WorkoutOutputDTO> workoutOutputDTOList = workoutService.getAllWorkouts();

		model.addAttribute("workout", workoutOutputDTOList.get(0));

		return "pdf/workout_pdf_template";
	}

	@PostMapping("/generate-pdf")
	public void generatePdf(@ModelAttribute WorkoutOutputDTO workout, HttpServletResponse response) throws IOException {
		// Convert WorkoutOutputDTO object to PDF and allow download
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=generated-pdf.pdf");

		PdfGenerator.generatePdfFromHtml(workout, response.getOutputStream());
	}

}