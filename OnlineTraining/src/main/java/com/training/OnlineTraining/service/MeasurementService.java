package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;

import java.util.List;
import java.util.UUID;


public interface MeasurementService {

	void createMeasurement(MeasurementDTO measurementDTO);
	public List<Measurement> getMeasurementsByContractIdSortedByDate(UUID contractId);
	public MeasurementDTO getMeasurementById(UUID measurementId);

	public void updateMeasurement(UUID measurementId, MeasurementDTO measurementDto);

	public void deleteMeasurement(UUID measurementId);

}