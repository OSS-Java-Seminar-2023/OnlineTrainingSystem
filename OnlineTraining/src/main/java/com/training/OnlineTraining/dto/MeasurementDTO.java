package com.training.OnlineTraining.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {


	private Double bodyWeight;

	private Double bodyFat;

	private Double waistCircumference;

	private Double chestCircumference;

	private Double armCircumference;

	private Double legCircumference;
}
