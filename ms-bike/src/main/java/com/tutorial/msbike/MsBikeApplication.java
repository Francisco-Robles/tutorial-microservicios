package com.tutorial.msbike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBikeApplication.class, args);
	}

}
