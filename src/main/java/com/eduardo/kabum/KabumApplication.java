package com.eduardo.kabum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class KabumApplication {

	public static void main(String[] args) {
		SpringApplication.run(KabumApplication.class, args);
	}
}