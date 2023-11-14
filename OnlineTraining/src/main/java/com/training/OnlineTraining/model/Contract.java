package com.training.OnlineTraining.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Table
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "starting_measurement_id", referencedColumnName = "id")
    private Measurement startingMeasurement;

    @ManyToOne
    @JoinColumn(name = "goal_measurement_id", referencedColumnName = "id")
    private Measurement goalMeasurement;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "monthly_price")
    private BigDecimal monthlyPrice;
}
