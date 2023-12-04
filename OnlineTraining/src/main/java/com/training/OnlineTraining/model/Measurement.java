package com.training.OnlineTraining.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Data
@Table
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

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
}
