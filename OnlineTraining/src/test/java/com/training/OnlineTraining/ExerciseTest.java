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
import java.util.Optional;

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

	private ExerciseDTO exerciseDTO;

	@BeforeEach
	public void setUp(){
		exerciseService.deleteAll();

		exerciseDTO = new ExerciseDTO("Exercise 1", "Description 1", "NO", "LOW");
	}

	@Test
	public void testCreateExercise() {
		Exercise newExercise = exerciseService.createExercise(exerciseDTO);

		assertNotNull(newExercise);
		assertEquals("Exercise 1", newExercise.getName());

		List<Exercise> exercises = exerciseService.getAllExercises();
		assertEquals(1, exercises.size());
		assertEquals("Description 1", exercises.get(0).getDescription());

	}

	@Test
	public void testGetAllExercises() {

		exerciseService.createExercise(exerciseDTO);

		ExerciseDTO exerciseDTO2 = new ExerciseDTO("Exercise 2", "Description 2", "NO", "LOW");
		exerciseService.createExercise(exerciseDTO2);

		ExerciseDTO exerciseDTO3 = new ExerciseDTO("Exercise 3", "Description 3", "NO", "LOW");
		exerciseService.createExercise(exerciseDTO3);


		List<Exercise> exercises = exerciseService.getAllExercises();
		assertEquals(3, exercises.size());
	}

	@Test
	public void testGetExerciseById() {

		Exercise newExercise = exerciseService.createExercise(exerciseDTO);

		Optional<Exercise> getetedExercise = exerciseService.getExerciseById(newExercise.getId());

		assertNotNull(getetedExercise);
		assertEquals("Exercise 1", getetedExercise.get().getName());
	}

	@Test
	public void testUpdateExercise() {

		Exercise newExercise = exerciseService.createExercise(exerciseDTO);

		ExerciseDTO updatedExerciseDTO = new ExerciseDTO("Exercise UPDATE", "Description UPDATE", "NO", "LOW");

		Exercise updateExercise = exerciseService.updateExercise(newExercise.getId(), updatedExerciseDTO);

		assertNotNull(updateExercise);
		assertEquals("Exercise UPDATE", updateExercise.getName());
		assertEquals("Description UPDATE", updateExercise.getDescription());
	}

	@Test
	public void testDeleteExercise() {
		Exercise newExercise = exerciseService.createExercise(exerciseDTO);
		exerciseService.deleteExercise(newExercise.getId());

		assertEquals(0, exerciseService.getAllExercises().size());
	}


}
