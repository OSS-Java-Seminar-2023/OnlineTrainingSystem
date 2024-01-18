package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "User_table", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String country;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String gender;

    @Column
    private Integer age;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public User(UUID id) {
        this.id = id;
    }
}