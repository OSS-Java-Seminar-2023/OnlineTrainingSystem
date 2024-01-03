package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.service.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class PdfController {

	@GetMapping("/show-pdf")
	public String showPdf(Model model) {
		// Add data to be displayed in the Thymeleaf template
		model.addAttribute("message", "Hello, this is data for the PDF!");

		// Return the name of the Thymeleaf template to be rendered
		return "pdf/pdf_template";
	}

	@PostMapping("/generate-pdf")
	public void generatePdf(@RequestParam("htmlContent") String htmlContent, HttpServletResponse response) throws IOException {
		// Convert HTML content to PDF and allow download
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=generated-pdf.pdf");

		PdfGenerator.generatePdfFromHtml(htmlContent, response.getOutputStream());
	}
}