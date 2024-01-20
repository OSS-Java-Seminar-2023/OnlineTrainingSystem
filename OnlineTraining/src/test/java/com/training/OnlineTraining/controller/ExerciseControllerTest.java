package com.training.OnlineTraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.OnlineTraining.dto.input.ExerciseInputDTO;
import com.training.OnlineTraining.dto.output.ExerciseOutputDTO;
import com.training.OnlineTraining.model.enums.ExerciseDifficultyLevel;
import com.training.OnlineTraining.model.enums.ExerciseEquipment;
import com.training.OnlineTraining.repository.ClientRepository;
import com.training.OnlineTraining.repository.MeasurementRepository;
import com.training.OnlineTraining.repository.UserRepository;
import com.training.OnlineTraining.service.ExerciseService;
import com.training.OnlineTraining.service.MeasurementService;
import com.training.OnlineTraining.service.WorkoutSessionService;
import com.training.OnlineTraining.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class ExerciseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExerciseService exerciseService; // Mock the ExerciseService

	@InjectMocks
	private ExerciseController exerciseController;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void showAddExerciseForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/exercise/create"))
				.andExpect(status().isOk())
				.andExpect(view().name("exercise/exerciseCreateForm"))
				.andExpect(model().attribute("difficultyLevels", hasSize(ExerciseDifficultyLevel.values().length)))
				.andExpect(model().attribute("exerciseEquipmentList", hasSize(ExerciseEquipment.values().length)))
				.andExpect(model().attribute("exercise", instanceOf(ExerciseInputDTO.class)));
	}

	@Test
	void createExercise() throws Exception {
		ExerciseInputDTO inputDTO = new ExerciseInputDTO();
		ExerciseOutputDTO outputDTO = new ExerciseOutputDTO();
		when(exerciseService.createExercise(any())).thenReturn(outputDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/exercise/create")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.flashAttr("exercise", inputDTO))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/exercise"));

		verify(exerciseService, times(1)).createExercise(any());
	}

	@Test
	void showExerciseDetails() throws Exception {
		UUID exerciseId = UUID.randomUUID();
		ExerciseOutputDTO outputDTO = new ExerciseOutputDTO();
		when(exerciseService.getExerciseById(exerciseId)).thenReturn(outputDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/exercise/details/{id}", exerciseId))
				.andExpect(status().isOk())
				.andExpect(view().name("exercise/exerciseDetails"))
				.andExpect(model().attribute("exercise", outputDTO));
	}

	@Test
	@WithMockUser(authorities = {"ADMIN", "COACH"})
	void getAllExercises() throws Exception {
		List<ExerciseOutputDTO> exercises = Arrays.asList(new ExerciseOutputDTO(), new ExerciseOutputDTO());
		when(exerciseService.getAllExercises()).thenReturn(exercises);

		mockMvc.perform(MockMvcRequestBuilders.get("/exercise"))
				.andExpect(status().isOk())
				.andExpect(view().name("exercise/exerciseList"))
				.andExpect(model().attribute("exercises", hasSize(2)));
	}


	@Test
	void getUpdateFormForExercise() throws Exception {
		UUID exerciseId = UUID.randomUUID();
		ExerciseOutputDTO outputDTO = new ExerciseOutputDTO();
		List<ExerciseDifficultyLevel> difficultyLevels = Arrays.asList(ExerciseDifficultyLevel.values());
		List<ExerciseEquipment> exerciseEquipmentList = Arrays.asList(ExerciseEquipment.values());

		when(exerciseService.getExerciseById(exerciseId)).thenReturn(outputDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/exercise/update/{id}", exerciseId))
				.andExpect(status().isOk())
				.andExpect(view().name("exercise/exerciseUpdateForm"))
				.andExpect(model().attribute("exerciseEquipmentList", exerciseEquipmentList))
				.andExpect(model().attribute("difficultyLevels", difficultyLevels))
				.andExpect(model().attribute("exerciseForUpdating", outputDTO));
	}

	@Test
	void updateExercise() throws Exception {
		UUID exerciseId = UUID.randomUUID();
		ExerciseInputDTO inputDTO = new ExerciseInputDTO();

		mockMvc.perform(MockMvcRequestBuilders.post("/exercise/update/{id}", exerciseId)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.flashAttr("exercise", inputDTO))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/exercise"));

		verify(exerciseService, times(1)).updateExercise(eq(exerciseId), ArgumentMatchers.any(ExerciseInputDTO.class));
	}

	@Test
	void deleteExercise() throws Exception {
		UUID exerciseId = UUID.randomUUID();

		mockMvc.perform(MockMvcRequestBuilders.post("/exercise/delete/{id}", exerciseId))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/exercise"));

		verify(exerciseService, times(1)).deleteExercise(eq(exerciseId));
	}

	@Test
	void showClientExerciseDetails() throws Exception {
		UUID exerciseId = UUID.randomUUID();
		ExerciseOutputDTO outputDTO = new ExerciseOutputDTO();
		when(exerciseService.getExerciseById(exerciseId)).thenReturn(outputDTO);

		mockMvc.perform(MockMvcRequestBuilders.get("/exercise/client-details/{id}", exerciseId))
				.andExpect(status().isOk())
				.andExpect(view().name("client/client_exercise_details"))
				.andExpect(model().attribute("exercise", outputDTO));
	}

	@Test
	void showError() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/exercise/progress"))
				.andExpect(status().isOk())
				.andExpect(view().name("client/no_exercise_found"));
	}

}
