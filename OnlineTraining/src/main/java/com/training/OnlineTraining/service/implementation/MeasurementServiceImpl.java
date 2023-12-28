package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.MeasurementDTO;

import com.training.OnlineTraining.exceptions.MeasurementNotFoundException;
import com.training.OnlineTraining.mapper.MeasurementMapper;
import com.training.OnlineTraining.model.Contract;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.repository.ContractRepository;
import com.training.OnlineTraining.repository.MeasurementRepository;
import com.training.OnlineTraining.service.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MeasurementServiceImpl implements MeasurementService {

	private final MeasurementRepository measurementRepository;
	private MeasurementMapper measurementMapper;
	private ContractRepository contractRepository;


	@Override
	public void createMeasurement(MeasurementDTO measurementDto) {
		Measurement measurement = measurementMapper.toMeasurement(measurementDto);
		measurementRepository.save(measurement);
	}

	@Override
	public List<Measurement> getMeasurementsByContractIdSortedByDate(UUID contractId) {


        return measurementRepository.findByContractIdOrderByMeasurementDateDesc(contractId);
	}

	@Override
	public MeasurementDTO getMeasurementById(UUID measurementId) {
		Measurement measurement = measurementRepository.findById(measurementId)
				.orElseThrow(() -> new MeasurementNotFoundException(measurementId));
		return measurementMapper.toMeasurementDTO(measurement);
	}

	@Override
	public void updateMeasurement(UUID measurementId, MeasurementDTO measurementDto) {

		Measurement existingMeasurement = measurementRepository.findById(measurementId)
				.orElseThrow(() -> new MeasurementNotFoundException(measurementId));

		Measurement updatedMeasurement = measurementMapper.toMeasurement(measurementDto);
		updatedMeasurement.setId(existingMeasurement.getId());

		measurementRepository.save(updatedMeasurement);
	}

	public void deleteMeasurement(UUID measurementId) {
		measurementRepository.deleteById(measurementId);
	}



}