package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WorkoutService workoutService; // Mock the ExerciseService

	@InjectMocks
	private AdminController adminController;

	@Test
	void showPdf() throws Exception {
		List<WorkoutOutputDTO> workoutOutputDTOList = Arrays.asList(new WorkoutOutputDTO()); // Create a sample workout

		when(workoutService.getAllWorkouts()).thenReturn(workoutOutputDTOList);

		mockMvc.perform(get("/show-pdf"))
				.andExpect(status().isOk())
				.andExpect(view().name("pdf/workout_pdf_template"))
				.andExpect(model().attribute("workout", workoutOutputDTOList.get(0)));
	}

	@Test
	void generatePdf() throws Exception {
		WorkoutOutputDTO workoutOutputDTO = new WorkoutOutputDTO(); // Create a sample workout

		when(workoutService.getWorkoutById(any(UUID.class))).thenReturn(workoutOutputDTO);

		mockMvc.perform(post("/generate-pdf")
						.flashAttr("workout", workoutOutputDTO))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/pdf"))
				.andExpect(header().string("Content-Disposition", "attachment; filename=generated-pdf.pdf"));
	}


}
