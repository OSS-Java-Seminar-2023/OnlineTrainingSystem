package com.training.OnlineTraining.dto;

import lombok.Data;

@Data
public class CoachFilterParams {
    private String gender;
    private Double experience;
    private Integer age;
    private String education;
    private Double monthlyPrice;

}