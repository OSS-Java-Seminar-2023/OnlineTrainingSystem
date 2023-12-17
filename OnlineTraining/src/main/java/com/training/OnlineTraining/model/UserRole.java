package com.training.OnlineTraining.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_role")
@Data
@AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

}

