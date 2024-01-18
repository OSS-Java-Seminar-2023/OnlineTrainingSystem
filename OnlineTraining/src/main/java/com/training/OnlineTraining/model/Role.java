package com.training.OnlineTraining.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", columnDefinition = "VARCHAR(255)") // Adjust the column definition as needed
	private String name;

	private Role() {

	}

	private Role(String name) {

		this.name = name;
	}

	public static Role getClientRole(){
		return  new Role("CLIENT");
	}

	public static Role getCoachRole(){
		return  new Role("COACH");
	}

	@Override
	public String toString() {
		return name;
	}

}