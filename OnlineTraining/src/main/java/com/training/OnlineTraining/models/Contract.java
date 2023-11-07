package com.training.OnlineTraining.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "Contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID")
    private UUID id = UUID.randomUUID();

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

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "monthly_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal monthlyPrice;
}
