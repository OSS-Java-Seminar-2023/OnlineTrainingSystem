package com.training.OnlineTraining.model;

import com.training.OnlineTraining.dto.MeasurementDTO;
import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @Column(name = "body_weight")
    private Double bodyWeight;

    @Column(name = "body_fat")
    private Double bodyFat;

    @Column(name = "waist_circumference")
    private Double waistCircumference;

    @Column(name = "chest_circumference")
    private Double chestCircumference;

    @Column(name = "arm_circumference")
    private Double armCircumference;

    @Column(name = "leg_circumference")
    private Double legCircumference;

    public void updateValues(MeasurementDTO measurementDTO) {
        this.setBodyWeight(measurementDTO.getBodyWeight());
        this.setBodyFat(measurementDTO.getBodyFat());
        this.setWaistCircumference(measurementDTO.getWaistCircumference());
        this.setChestCircumference(measurementDTO.getChestCircumference());
        this.setArmCircumference(measurementDTO.getArmCircumference());
        this.setLegCircumference(measurementDTO.getLegCircumference());
    }

}
