package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ClientDto;
import com.training.OnlineTraining.dto.UpdateClientDTO;
import com.training.OnlineTraining.model.Client;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.service.ClientService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.UUID;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService clientService; // Mock the ExerciseService

	@InjectMocks
	private ClientController clientController;

	@Test
	@WithMockUser(authorities = {"ADMIN", "CLIENT"})
	void getClientPage() throws Exception {
		mockMvc.perform(get("/clients/client-page"))
				.andExpect(status().isOk())
				.andExpect(view().name("client/client_page"));
	}

	@Test
	@WithMockUser(authorities = {"ADMIN", "CLIENT"})
	void getBecomeClientPage() throws Exception {
		UUID userId = UUID.randomUUID();
		mockMvc.perform(get("/clients/register").param("userId", userId.toString()))
				.andExpect(status().isOk())
				.andExpect(view().name("client/client_register_page"))
				.andExpect(model().attribute("userId", userId))
				.andExpect(model().attribute("client", instanceOf(ClientDto.class)));
	}

	@Test
	@WithMockUser(authorities = {"ADMIN", "CLIENT"})
	void becomeClient() throws Exception {
		UUID userId = UUID.randomUUID();
		ClientDto clientDto = new ClientDto(); // Add necessary attributes for the client DTO
		mockMvc.perform(post("/clients/register")
						.param("userId", userId.toString())
						.flashAttr("clientDto", clientDto))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/login"));
	}

	@Test
	@WithMockUser(authorities = {"CLIENT"})
	void getSettings() throws Exception {
		UUID clientId = UUID.randomUUID();
		Client client = new Client();
		client.setId(clientId);
		client.setUser(new User());

		when(clientService.getClientsById(clientId)).thenReturn(client);

		mockMvc.perform(get("/clients/settings").sessionAttr("clientId", clientId))
				.andExpect(status().isOk())
				.andExpect(view().name("client/settings"))
				.andExpect(model().attribute("client", instanceOf(Client.class)))
				.andExpect(model().attribute("genderOptions", hasSize(2))); // Adjust the size based on your options
	}

	@Test
	@WithMockUser(authorities = {"ADMIN", "CLIENT"})
	void updateClient() throws Exception {
		UUID clientId = UUID.randomUUID();
		UpdateClientDTO updateClientDTO = new UpdateClientDTO(); // Add necessary attributes for the update DTO
		mockMvc.perform(put("/clients/update").sessionAttr("clientId", clientId)
						.flashAttr("updateClientDTO", updateClientDTO))
				.andExpect(view().name("index"));
	}



}
