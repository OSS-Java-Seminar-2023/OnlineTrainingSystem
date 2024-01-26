package com.training.OnlineTraining.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ReportGenerator {

	private static final TemplateEngine templateEngine = new SpringTemplateEngine();

	public static void generateReportFromHtml(WorkoutOutputDTO workout, OutputStream outputStream) {
		// Load the Thymeleaf HTML template
		String template = loadTemplate("report/workout_report_template.html");

		// Create Thymeleaf context and set the workout DTO as a variable
		Context context = new Context();
		context.setVariable("workout", workout);

		// Process the template with Thymeleaf
		String processedHtml = templateEngine.process(template, context);

		// Convert processed HTML to PDF and write to the output stream
		ConverterProperties converterProperties = new ConverterProperties();
		HtmlConverter.convertToPdf(processedHtml, outputStream, converterProperties);
	}

	private static String loadTemplate(String templateName) {
		try (InputStream inputStream = ReportGenerator.class.getResourceAsStream("/templates/" + templateName);
		     InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

			StringBuilder template = new StringBuilder();
			char[] buffer = new char[1024];
			int bytesRead;
			while ((bytesRead = reader.read(buffer)) != -1) {
				template.append(buffer, 0, bytesRead);
			}

			return template.toString();

		} catch (IOException e) {
			throw new RuntimeException("Error loading Thymeleaf template", e);
		}
	}
}
