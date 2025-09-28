package com.lutfudolay.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.lutfudolay"})
@EntityScan(basePackages = {"com.lutfudolay.entities"})
@EnableJpaRepositories(basePackages = {"com.lutfudolay.repository"})
@SpringBootApplication
public class AtmProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmProjectApplication.class, args);
	}
}
