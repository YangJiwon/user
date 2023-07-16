package com.project.ably;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AblyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AblyApplication.class, args);
	}

}
