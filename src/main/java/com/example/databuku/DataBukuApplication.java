package com.example.databuku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DataBukuApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBukuApplication.class, args);
	}

}
