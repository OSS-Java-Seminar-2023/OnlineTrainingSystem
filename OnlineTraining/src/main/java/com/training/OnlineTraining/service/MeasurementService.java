package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MeasurementService {
	Measurement createMeasurement(MeasurementDTO measurementDTO);
	Optional<Measurement> getMeasurementById(UUID id);
	List<Measurement> getAllMeasurements();
	Measurement updateMeasurement(UUID id, MeasurementDTO measurementDetails);
	void deleteMeasurement(UUID id);
	void deleteAll();
}