package com.idle.fmd;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FmdApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmdApplication.class, args);
	}

}
