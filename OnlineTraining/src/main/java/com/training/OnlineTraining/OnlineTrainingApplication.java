package com.training.OnlineTraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.training.OnlineTraining")
@SpringBootApplication
@EntityScan("com.training.OnlineTraining.model")
public class OnlineTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTrainingApplication.class, args);
	}
}
