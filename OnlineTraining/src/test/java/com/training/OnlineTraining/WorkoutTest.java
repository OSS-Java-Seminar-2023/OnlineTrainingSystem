package com.training.OnlineTraining;

import com.training.OnlineTraining.dto.ExerciseDTO;
import com.training.OnlineTraining.dto.WorkoutDTO;
import com.training.OnlineTraining.model.Contract;
import com.training.OnlineTraining.model.Exercise;
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

	private WorkoutDTO workoutDTO;

	private UUID contractID;

	private int numberOfWorkoutsInDatabaseBeforeTest = 0;

	@BeforeEach
	public void setUp(){
		numberOfWorkoutsInDatabaseBeforeTest = workoutService.getAllWorkouts().size();

		contractID = contractService.getAllContracts().get(0).getId();

		workoutDTO = TestDTOUtils.getWorkoutDTO(contractID);
	}

	@Test
	public void testCreateWorkout() {

		workoutDTO.setSelfRating(1000);

		Workout newWorkout = workoutService.createWorkout(workoutDTO);

		assertNotNull(newWorkout);
		assertEquals(1000, newWorkout.getSelfRating());

		List<Workout> workouts = workoutService.getAllWorkouts();
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest + 1, workouts.size());
	}

	@Test
	public void testGetAllExercises() {
		workoutService.createWorkout(workoutDTO);

		WorkoutDTO workoutDTO1 = TestDTOUtils.getWorkoutDTO(contractID);
		workoutService.createWorkout(workoutDTO1);

		WorkoutDTO workoutDTO2 = TestDTOUtils.getWorkoutDTO(contractID);
		workoutService.createWorkout(workoutDTO2);

		List<Workout> workouts = workoutService.getAllWorkouts();
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest + 3, workouts.size());
	}

	@Test
	public void testGetExerciseById() {
		workoutDTO.setSelfRating(1000);

		Workout newWorkout = workoutService.createWorkout(workoutDTO);

		Workout retreivedWorkout = workoutService.getWorkoutById(newWorkout.getId());

		assertNotNull(retreivedWorkout);
		assertEquals(1000, newWorkout.getSelfRating());
	}

	@Test
	public void testUpdateExercise() {
		workoutDTO.setSelfRating(1000);

		Workout newWorkout = workoutService.createWorkout(workoutDTO);

		WorkoutDTO updatedWorkoutDTO = TestDTOUtils.getWorkoutDTO(contractID);
		updatedWorkoutDTO.setSelfRating(5500);

		Workout updateWorkout = workoutService.updateWorkout(newWorkout.getId(), updatedWorkoutDTO);

		assertNotNull(updateWorkout);
		assertEquals(5500, updateWorkout.getSelfRating());
	}

	@Test
	public void testDeleteExercise() {
		Workout newWorkout = workoutService.createWorkout(workoutDTO);
		workoutService.deleteWorkout(newWorkout.getId());
		assertEquals(numberOfWorkoutsInDatabaseBeforeTest, workoutService.getAllWorkouts().size());
	}


}
