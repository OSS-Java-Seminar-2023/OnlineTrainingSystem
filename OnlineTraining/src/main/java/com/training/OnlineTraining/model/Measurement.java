package com.training.OnlineTraining.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Data
@Table
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "body_weight")
    private BigDecimal bodyWeight;

    @Column(name = "body_fat")
    private BigDecimal bodyFat;

    @Column(name = "waist_circumference")
    private BigDecimal waistCircumference;

    @Column(name = "chest_circumference")
    private BigDecimal chestCircumference;

    @Column(name = "arm_circumference")
    private BigDecimal armCircumference;

    @Column(name = "leg_circumference")
    private BigDecimal legCircumference;
}
