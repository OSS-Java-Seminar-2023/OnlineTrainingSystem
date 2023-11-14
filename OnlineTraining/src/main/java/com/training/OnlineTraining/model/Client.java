package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "medical_condition", columnDefinition = "TEXT")
    private String medicalCondition;

    @Column(name = "injuries", columnDefinition = "TEXT")
    private String injuries;
}

