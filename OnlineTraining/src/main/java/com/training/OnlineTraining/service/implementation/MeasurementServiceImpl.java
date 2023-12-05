package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.MeasurementDTO;
import com.training.OnlineTraining.exceptions.MeasurementNotFoundException;
import com.training.OnlineTraining.mapper.MeasurementMapper;
import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.repository.MeasurementRepository;
import com.training.OnlineTraining.service.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MeasurementServiceImpl implements MeasurementService {

	private final MeasurementRepository measurementRepository;

	private final MeasurementMapper measurementMapper;

	public MeasurementServiceImpl(MeasurementRepository measurementRepository, MeasurementMapper measurementMapper) {
		this.measurementRepository = measurementRepository;
		this.measurementMapper = measurementMapper;
	}

	@Override
	public Measurement createMeasurement(MeasurementDTO measurementDTO) {
		Measurement measurement = measurementMapper.toMeasurement(measurementDTO);
		return measurementRepository.save(measurement);
	}

	@Override
	public Optional<Measurement> getMeasurementById(UUID id) {
		return measurementRepository.findById(id);
	}

	@Override
	public List<Measurement> getAllMeasurements() {
		return measurementRepository.findAll();
	}

	@Override
	public Measurement updateMeasurement(UUID id, MeasurementDTO measurementDetails) {
		return measurementRepository.findById(id)
				.map(measurement -> {
					measurement.updateValues(measurementDetails);
					return measurementRepository.save(measurement);
				})
				.orElseThrow(() -> new MeasurementNotFoundException(id));
	}

	@Override
	public void deleteMeasurement(UUID id) {
		measurementRepository.findById(id)
				.ifPresentOrElse(
						measurementRepository::delete,
						() -> {
							throw new MeasurementNotFoundException(id);
						}
				);
	}

	@Override
	public void deleteAll() {
		this.measurementRepository.deleteAll();
	}
}