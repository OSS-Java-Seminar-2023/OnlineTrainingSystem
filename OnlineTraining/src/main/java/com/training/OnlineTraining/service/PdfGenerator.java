package com.training.OnlineTraining.service;



import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import java.io.OutputStream;

public class PdfGenerator {

	public static void generatePdfFromHtml(String htmlContent, OutputStream outputStream) {
		ConverterProperties converterProperties = new ConverterProperties();
		HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);
	}
}
