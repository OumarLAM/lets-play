package com.example.oulam.lets_play;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LetsPlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsPlayApplication.class, args);
		System.out.println("Let's play app is running succesfully...");
		System.out.println("You can access the application at http://localhost:8443");
	}

}
