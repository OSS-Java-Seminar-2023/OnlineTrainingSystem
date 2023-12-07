package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.Contract;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO {

	private Contract contract;

	private Date measurementDate;

	private Double bodyWeight;

	private Double bodyFat;

	private Double waistCircumference;

	private Double chestCircumference;

	private Double armCircumference;

	private Double legCircumference;

}
