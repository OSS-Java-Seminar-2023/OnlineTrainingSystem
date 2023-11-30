package com.training.OnlineTraining;

import com.training.OnlineTraining.controller.ExerciseController;
import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.service.ExerciseService;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = OnlineTrainingApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		FlywayTestExecutionListener.class
})
public class ExerciseTest {

	@Autowired
	private ExerciseController exerciseController;

	@Autowired
	private ExerciseService exerciseService;

	@BeforeEach
	public void setUp(){
		exerciseService.deleteAll();
	}

	@Test
	public void testCreateAddress() {

		ExerciseDTO exerciseDTO = new ExerciseDTO("Exercise 1", "Description 1", "NO", "LOW");
		ResponseEntity<Exercise> responseEntity = exerciseController.createExercise(exerciseDTO);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals("Exercise 1", responseEntity.getBody().getName());

		List<Exercise> exercises = exerciseService.getAllExercises();
		assertEquals(1, exercises.size());
		assertEquals("Description 1", exercises.get(0).getDescription());

	}
}
