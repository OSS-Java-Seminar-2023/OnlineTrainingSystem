package com.training.OnlineTraining;

import com.training.OnlineTraining.controller.MeasurementController;
import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Exercise;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.service.MeasurementService;
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
public class MeasurementTest {

	@Autowired
	private MeasurementController measurementController;

	@Autowired
	private MeasurementService measurementService;

	@BeforeEach
	public void setUp(){
		measurementService.deleteAll();
	}
	@Test
	public void testCreateMeasurement()  {
		MeasurementDTO measurementDTO = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		Measurement measurement = measurementService.createMeasurement(measurementDTO);

		assertNotNull(measurement);
		assertEquals(70.5,measurement.getBodyWeight());
	}

	@Test
	public void testGetAllMeasurements()  {
		MeasurementDTO measurementDTO1 = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		measurementService.createMeasurement(measurementDTO1);

		MeasurementDTO measurementDTO2 = new MeasurementDTO(72.0, 18.0, 85.0, 95.0, 32.0, 42.0);
		measurementService.createMeasurement(measurementDTO2);

		MeasurementDTO measurementDTO3 = new MeasurementDTO(71.2, 16.5, 82.0, 92.0, 31.0, 41.0);
		measurementService.createMeasurement(measurementDTO3);

		List<Measurement> measurementList = measurementService.getAllMeasurements();
		assertEquals(3, measurementList.size());
	}

	@Test
	public void testGetMeasurementById() throws Exception {
		MeasurementDTO measurementDTO = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		Measurement newMeasurement = measurementService.createMeasurement(measurementDTO);

		Optional<Measurement> retrievedMeasurement = measurementService.getMeasurementById(newMeasurement.getId());

		assertNotNull(retrievedMeasurement);
		assertEquals(70.5, retrievedMeasurement.get().getBodyWeight());
	}

	@Test
	public void testUpdateMeasurement() throws Exception {
		MeasurementDTO measurementDTO = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		Measurement newMeasurement = measurementService.createMeasurement(measurementDTO);

		MeasurementDTO updatedMeasurementDTO = new MeasurementDTO(72.0, 18.0, 85.0, 95.0, 32.0, 42.0);

		Measurement updatedMeasurement = measurementService.updateMeasurement(newMeasurement.getId(), updatedMeasurementDTO);

		assertNotNull(updatedMeasurement);
		assertEquals(72.0, updatedMeasurement.getBodyWeight());
	}

	@Test
	public void testDeleteMeasurement() throws Exception {
		MeasurementDTO measurementDTO = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		Measurement newMeasurement = measurementService.createMeasurement(measurementDTO);

		measurementService.deleteMeasurement(newMeasurement.getId());


		assertEquals(0, measurementService.getAllMeasurements().size());
	}

	@Test
	public void testDeleteAllMeasurements() throws Exception {
		MeasurementDTO measurementDTO1 = new MeasurementDTO(70.5, 15.0, 80.0, 90.0, 30.0, 40.0);
		measurementController.createMeasurement(measurementDTO1);

		MeasurementDTO measurementDTO2 = new MeasurementDTO(72.0, 18.0, 85.0, 95.0, 32.0, 42.0);
		measurementController.createMeasurement(measurementDTO2);

		measurementService.deleteAll();
		assertEquals(0, measurementService.getAllMeasurements().size());
	}

}