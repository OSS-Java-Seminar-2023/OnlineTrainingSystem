package com.training.OnlineTraining.controller;


import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

	private final MeasurementService measurementService;

	@Autowired
	public MeasurementController(MeasurementService measurementService) {
		this.measurementService = measurementService;
	}

	@PostMapping
	public ResponseEntity<Measurement> createMeasurement(@RequestBody MeasurementDTO measurementDTO) {
		Measurement createdMeasurement = measurementService.createMeasurement(measurementDTO);
		return new ResponseEntity<>(createdMeasurement, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Measurement> getMeasurementById(@PathVariable UUID id) {
		return measurementService.getMeasurementById(id)
				.map(measurement -> new ResponseEntity<>(measurement, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<Measurement>> getAllMeasurements() {
		List<Measurement> measurements = measurementService.getAllMeasurements();
		return new ResponseEntity<>(measurements, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Measurement> updateMeasurement(@PathVariable UUID id, @RequestBody MeasurementDTO measurementDTO) {
		Measurement updatedMeasurement = measurementService.updateMeasurement(id, measurementDTO);
		return new ResponseEntity<>(updatedMeasurement, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMeasurement(@PathVariable UUID id) {
		measurementService.deleteMeasurement(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllMeasurements() {
		measurementService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}