package com.training.OnlineTraining.service;

import com.training.OnlineTraining.OnlineTrainingApplication;
import com.training.OnlineTraining.dto.input.WorkoutInputDTO;
import com.training.OnlineTraining.dto.output.WorkoutOutputDTO;
import com.training.OnlineTraining.model.Workout;
import com.training.OnlineTraining.service.ContractService;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.utils.TestDTOUtils;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = OnlineTrainingApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		FlywayTestExecutionListener.class
})
public class WorkoutTest {

	@Autowired
	private WorkoutService workoutService;

	@Autowired
	private ContractService contractService;

	private WorkoutInputDTO workoutInputDTO;

	private UUID contractID;

	private int numberOfWorkoutsInDatabaseBeforeTest = 0;

	@BeforeEach
	public void setUp(){
		numberOfWorkoutsInDatabaseBeforeTest = workoutService.getAllWorkouts().size();

		contractID = contractService.getAllContracts().get(0).getId();

		workoutInputDTO = TestDTOUtils.getWorkoutDTO(contractID);
	}

	@Test
	public void testCreateWorkout() {

		workoutInputDTO.setSelfRating(1000);

		WorkoutOutputDTO newWorkout = workoutService.createWorkout(workoutInputDTO);

		assertNotNull(newWorkout);
		assertEquals(1000, newWorkout.getSelfRating());

		List<WorkoutOutputDTO> workouts = workoutService.getAllWorkouts();
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest + 1, workouts.size());
	}

	@Test
	public void testGetAllExercises() {
		workoutService.createWorkout(workoutInputDTO);

		WorkoutInputDTO workoutInputDTO1 = TestDTOUtils.getWorkoutDTO(contractID);
		workoutService.createWorkout(workoutInputDTO1);

		WorkoutInputDTO workoutInputDTO2 = TestDTOUtils.getWorkoutDTO(contractID);
		workoutService.createWorkout(workoutInputDTO2);

		List<WorkoutOutputDTO> workouts = workoutService.getAllWorkouts();
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest + 3, workouts.size());
	}

	@Test
	public void testGetExerciseById() {
		workoutInputDTO.setSelfRating(1000);

		WorkoutOutputDTO newWorkout = workoutService.createWorkout(workoutInputDTO);

		WorkoutOutputDTO retreivedWorkout = workoutService.getWorkoutById(newWorkout.getId());

		assertNotNull(retreivedWorkout);
		assertEquals(1000, newWorkout.getSelfRating());
	}

	@Test
	public void testUpdateExercise() {
		workoutInputDTO.setSelfRating(1000);

		WorkoutOutputDTO newWorkout = workoutService.createWorkout(workoutInputDTO);

		WorkoutInputDTO updatedWorkoutInputDTO = TestDTOUtils.getWorkoutDTO(contractID);
		updatedWorkoutInputDTO.setSelfRating(5500);

		WorkoutOutputDTO updateWorkout = workoutService.updateWorkout(newWorkout.getId(), updatedWorkoutInputDTO);

		assertNotNull(updateWorkout);
		assertEquals(5500, updateWorkout.getSelfRating());
	}

	@Test
	public void testDeleteExercise() {
		WorkoutOutputDTO newWorkout = workoutService.createWorkout(workoutInputDTO);
		workoutService.deleteWorkout(newWorkout.getId());
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest, workoutService.getAllWorkouts().size());
	}


}
