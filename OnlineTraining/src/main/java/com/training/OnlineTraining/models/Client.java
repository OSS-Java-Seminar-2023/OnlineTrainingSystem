package com.training.OnlineTraining.models;

import javax.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @Column(name = "medical_condition", columnDefinition = "TEXT")
    private String medicalCondition;

    @Column(name = "injuries", columnDefinition = "TEXT")
    private String injuries;
}

