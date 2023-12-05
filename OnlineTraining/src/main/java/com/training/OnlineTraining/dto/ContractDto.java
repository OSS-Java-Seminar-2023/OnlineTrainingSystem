package com.training.OnlineTraining.dto;

import com.training.OnlineTraining.model.Client;
import com.training.OnlineTraining.model.Coach;
import lombok.Data;

import java.util.UUID;

import java.sql.Date;
@Data
public class ContractDto {
    private UUID id;
    private Coach coach;
    private Client client;
    private Date startDate;
    private Date endDate;
    private Double monthlyPrice;
}

