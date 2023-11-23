package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
import com.training.OnlineTraining.model.enums.Education;

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
    private Double yearsOfExperience;


    @Enumerated(EnumType.STRING)
    @Column(name = "education")
    private Education education;

    @Column(name = "monthly_price")
    private Double monthlyPrice;
}