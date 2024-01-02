package com.training.OnlineTraining.converter;

import com.training.OnlineTraining.model.enums.WorkoutStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Converter(autoApply = true)
public class WorkoutStatusConverter implements AttributeConverter<WorkoutStatus, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkoutStatusConverter.class);

	@Override
	public String convertToDatabaseColumn(WorkoutStatus attribute) {
		LOGGER.info("Converting WorkoutStatus to database column: {}", attribute);
		return attribute.getDisplayName();
	}

	@Override
	public WorkoutStatus convertToEntityAttribute(String dbData) {
		LOGGER.info("Converting database column to WorkoutStatus: {}", dbData);

		if (dbData == null) {
			return null;
		}

		for (WorkoutStatus status : WorkoutStatus.values()) {
			if (status.getDisplayName().equals(dbData)) {
				LOGGER.info("Mapping database value {} to enum: {}", dbData, status);
				return status;
			}
		}
		LOGGER.warn("Unknown value received from database: {}", dbData);
		throw new IllegalArgumentException("Unknown database value: " + dbData);
	}
}