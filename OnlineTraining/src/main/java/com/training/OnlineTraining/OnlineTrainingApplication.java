package com.training.OnlineTraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OnlineTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineTrainingApplication.class, args);
	}

}
