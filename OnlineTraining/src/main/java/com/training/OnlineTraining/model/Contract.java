package com.training.OnlineTraining.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.UUID;
import java.sql.Date;

@Entity
@Data
@Table
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "monthly_price")
    private Double monthlyPrice;

}
