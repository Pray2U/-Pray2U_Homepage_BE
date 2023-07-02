package com.pray2you.p2uhomepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class P2uhomepageApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2uhomepageApplication.class, args);
	}

}
