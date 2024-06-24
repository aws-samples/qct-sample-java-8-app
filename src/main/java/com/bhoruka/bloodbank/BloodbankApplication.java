package com.bhoruka.bloodbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloodbankApplication {

	/**
	 * Method to start the spring boot app.
	 *
	 * @param args command line args
	 */
	public static void main(final String[] args) {
		SpringApplication application = new SpringApplication(BloodbankApplication.class);
		application.run(args);
	}
}
