package com.training.OnlineTraining;

import com.training.OnlineTraining.OnlineTrainingApplication;
import com.training.OnlineTraining.dto.WorkoutSessionDTO;
import com.training.OnlineTraining.model.WorkoutSession;
import com.training.OnlineTraining.service.ExerciseService;
import com.training.OnlineTraining.service.WorkoutService;
import com.training.OnlineTraining.service.WorkoutSessionService;
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
public class WorkoutSessionTest {

	@Autowired
	private WorkoutSessionService workoutSessionService;

	@Autowired
	private WorkoutService workoutService;

	@Autowired
	private ExerciseService exerciseService;

	private WorkoutSessionDTO workoutSessionDTO;

	private UUID workoutID = null, exerciseID = null;

	private int numberOfWorkoutSessionsInDatabaseBeforeTest = 0;

	@BeforeEach
	public void setUp() {
		numberOfWorkoutSessionsInDatabaseBeforeTest = workoutSessionService.getAllWorkoutSessions().size();

		workoutID = workoutService.getAllWorkouts().get(0).getId();
		exerciseID = exerciseService.getAllExercises().get(0).getId();

		workoutSessionDTO = TestDTOUtils.getWorkoutSessionDTO(workoutID, exerciseID); // Implement TestDTOUtils for WorkoutSessionDTO if not existing
	}

	@Test
	public void testCreateWorkoutSession() {
		WorkoutSession newWorkoutSession = workoutSessionService.createWorkoutSession(workoutSessionDTO);

		assertNotNull(newWorkoutSession);

		List<WorkoutSession> workoutSessions = workoutSessionService.getAllWorkoutSessions();
		assertEquals(numberOfWorkoutSessionsInDatabaseBeforeTest + 1, workoutSessions.size());
	}

	@Test
	public void testGetWorkoutSessionById() {
		workoutSessionDTO.setNumberOfReps(1000);

		WorkoutSession newWorkoutSession = workoutSessionService.createWorkoutSession(workoutSessionDTO);

		WorkoutSession retrievedWorkoutSession = workoutSessionService.getWorkoutSessionById(newWorkoutSession.getId());

		assertNotNull(retrievedWorkoutSession);
		assertEquals(1000, retrievedWorkoutSession.getNumberOfReps());
	}

	@Test
	public void testGetAllWorkoutSessions() {
		workoutSessionService.createWorkoutSession(workoutSessionDTO);
		workoutSessionService.createWorkoutSession(workoutSessionDTO);
		workoutSessionService.createWorkoutSession(workoutSessionDTO);

		List<WorkoutSession> workoutSessionList = workoutSessionService.getAllWorkoutSessions();

		assertNotNull(workoutSessionList);
		assertEquals(numberOfWorkoutSessionsInDatabaseBeforeTest + 3, workoutSessionList.size());
	}



	@Test
	public void testUpdateWorkoutSession() {
		WorkoutSession newWorkoutSession = workoutSessionService.createWorkoutSession(workoutSessionDTO);

		WorkoutSessionDTO updatedWorkoutSessionDTO = TestDTOUtils.getWorkoutSessionDTO(workoutID, exerciseID);

		updatedWorkoutSessionDTO.setNumberOfReps(1500);

		WorkoutSession updatedWorkoutSession = workoutSessionService.updateWorkoutSession(newWorkoutSession.getId(), updatedWorkoutSessionDTO);

		assertNotNull(updatedWorkoutSession);
		assertEquals(1500, updatedWorkoutSession.getNumberOfReps());
	}

	@Test
	public void testDeleteWorkoutSession() {
		WorkoutSession newWorkoutSession = workoutSessionService.createWorkoutSession(workoutSessionDTO);
		workoutSessionService.deleteWorkoutSession(newWorkoutSession.getId());
		assertEquals(numberOfWorkoutSessionsInDatabaseBeforeTest, workoutSessionService.getAllWorkoutSessions().size());
	}
}
