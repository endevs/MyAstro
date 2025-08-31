package com.astro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.astro", "com.astrology", "com.javatechie"})
@EnableJpaRepositories("com.astro.web.repository")
@EntityScan("com.astro.web.model")
public class MyAstroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAstroApplication.class, args);
	}

}
