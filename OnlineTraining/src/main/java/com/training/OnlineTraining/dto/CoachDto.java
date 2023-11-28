package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.enums.Education;
import lombok.Data;

@Data
public class CoachDto {
    private Double yearsOfExperience;
    private Education education;
    private Double monthlyPrice;
    private String userFirstName;
    private String userLastName;
    private String userCity;
    private String userCountry;
    private String userGender;
    private Integer userAge;
}
