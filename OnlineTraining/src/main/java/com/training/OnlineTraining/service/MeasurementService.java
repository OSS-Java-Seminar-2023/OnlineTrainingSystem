package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.Measurement;
import com.training.OnlineTraining.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MeasurementService {

    private final MeasurementRepository measurementRepository;



}
