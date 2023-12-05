package com.training.OnlineTraining.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class MeasurementDTO {


	private Double bodyWeight;

	private Double bodyFat;

	private Double waistCircumference;

	private Double chestCircumference;

	private Double armCircumference;

	private Double legCircumference;
}
