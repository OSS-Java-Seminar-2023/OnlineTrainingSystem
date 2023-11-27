package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.enums.Education;
import lombok.Data;

@Data
public class CoachDto {
    private Double yearsOfExperience;
    private Education education;
    private Double monthlyPrice;
}
