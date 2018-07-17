package com.teamcharm.review;
import org.springframework.boot.autoconfigure.SpringBootApplication;;

import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ReviewApplication {

	public static void main(String[] args) {
                new SpringApplicationBuilder(ReviewApplication.class)
				.properties("spring.config.name:application,config")
				.build().run(args);
	}
}
