package com.training.OnlineTraining.converter;

import com.training.OnlineTraining.model.enums.ExerciseEquipment;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class ExerciseEquipmentConverter implements AttributeConverter<ExerciseEquipment, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseEquipmentConverter.class);

	@Override
	public String convertToDatabaseColumn(ExerciseEquipment attribute) {
		LOGGER.info("Converting ExerciseEquipment to database column: {}", attribute);
		return attribute == null ? null : attribute.getDisplayName();
	}

	@Override
	public ExerciseEquipment convertToEntityAttribute(String dbData) {
		LOGGER.info("Converting database column to ExerciseEquipment: {}", dbData);

		if (dbData == null) {
			return null;
		}

		for (ExerciseEquipment equipment : ExerciseEquipment.values()) {
			if (equipment.getDisplayName().equalsIgnoreCase(dbData)) {
				LOGGER.info("Mapping database value {} to enum: {}", dbData, equipment);
				return equipment;
			}
		}
		LOGGER.warn("Unknown value received from database: {}", dbData);
		return null; // Or throw an exception for unknown values
	}
}