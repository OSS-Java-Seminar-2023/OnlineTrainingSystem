package com.training.OnlineTraining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public class MeasurementNotFoundException extends RuntimeException {
	public MeasurementNotFoundException(UUID id) {
		super("Measurement with ID " + id + " not found");
	}
}