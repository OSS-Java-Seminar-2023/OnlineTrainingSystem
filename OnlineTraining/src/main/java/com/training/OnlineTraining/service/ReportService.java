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

public interface ReportService {



	void generateReportFromHtml(WorkoutOutputDTO workout, OutputStream outputStream);


}
