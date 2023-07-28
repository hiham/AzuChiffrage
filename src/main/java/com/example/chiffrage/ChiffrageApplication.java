package com.example.chiffrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
public class ChiffrageApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(ChiffrageApplication.class);
		springApplication.setAdditionalProfiles("dev");
		springApplication.run(args);
	}

}
