package com.training.OnlineTraining.models;

import javax.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Coach")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;

    @Column(name = "education", nullable = false, columnDefinition = "TEXT")
    private String education;

    @Column(name = "monthly_price", nullable = false, columnDefinition = "NUMERIC(10, 2)")
    private Double monthlyPrice;
}
