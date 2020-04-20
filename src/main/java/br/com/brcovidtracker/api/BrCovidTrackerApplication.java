package br.com.brcovidtracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrCovidTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrCovidTrackerApplication.class, args);
	}

}
