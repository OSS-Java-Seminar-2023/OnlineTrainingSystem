package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column
    private String education;

    @Column(name = "monthly_price")
    private Double monthlyPrice;
}