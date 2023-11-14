package com.training.OnlineTraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.training.OnlineTraining.model"})
public class OnlineTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTrainingApplication.class, args);
	}
}
