package com.training.OnlineTraining.model;

import javax.persistence.*;
import lombok.*;
import java.util.UUID;

@Table
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String gender;

    @Column
    private Integer age;

    @Column
    private String password;
}
