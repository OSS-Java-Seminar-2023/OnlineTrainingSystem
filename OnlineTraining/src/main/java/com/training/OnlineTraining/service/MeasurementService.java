package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.model.Measurement;

import java.util.List;
import java.util.UUID;


public interface MeasurementService {

	Measurement createMeasurement(MeasurementDTO measurementDTO);
	List<Measurement> getMeasurementsByContractIdSortedByDate(UUID contractId);
	MeasurementDTO getMeasurementById(UUID measurementId);
	MeasurementDTO updateMeasurement(UUID measurementId, MeasurementDTO measurementDto);
	void deleteMeasurement(UUID measurementId);
	public List<Measurement> getMeasurementsByContractIdSortedByDateAsc(UUID contractId);

	List<Measurement> getAllMeasurements();

	int countMeasurements();


}