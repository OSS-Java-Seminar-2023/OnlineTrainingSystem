package com.training.OnlineTraining.service;

import com.training.OnlineTraining.repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

}
