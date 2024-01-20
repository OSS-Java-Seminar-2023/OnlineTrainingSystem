package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) ||
                (Objects.equals(user, coach.user) && Objects.equals(yearsOfExperience, coach.yearsOfExperience) && education == coach.education && Objects.equals(monthlyPrice, coach.monthlyPrice));
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, yearsOfExperience, education, monthlyPrice);
    }

}