package com.training.OnlineTraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id = UUID.randomUUID();

    @Column(name = "body_weight", nullable = false, precision = 6, scale = 2)
    private BigDecimal bodyWeight;

    @Column(name = "body_fat", nullable = false, precision = 5, scale = 2)
    private BigDecimal bodyFat;

    @Column(name = "waist_circumference", nullable = false, precision = 5, scale = 2)
    private BigDecimal waistCircumference;

    @Column(name = "chest_circumference", nullable = false, precision = 5, scale = 2)
    private BigDecimal chestCircumference;

    @Column(name = "arm_circumference", nullable = false, precision = 5, scale = 2)
    private BigDecimal armCircumference;

    @Column(name = "leg_circumference", nullable = false, precision = 5, scale = 2)
    private BigDecimal legCircumference;
}
