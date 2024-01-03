package com.training.OnlineTraining.service;



import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;

import java.io.OutputStream;

public class PdfGenerator {

	public static void generatePdfFromHtml(WorkoutOutputDTO workout, OutputStream outputStream) {
		String htmlContent = workout.toString(); // Convert WorkoutOutputDTO to HTML content

		ConverterProperties converterProperties = new ConverterProperties();
		HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);
	}
}
