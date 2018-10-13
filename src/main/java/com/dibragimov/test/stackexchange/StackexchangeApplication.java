package com.dibragimov.test.stackexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class})
@EnableAsync
public class StackexchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StackexchangeApplication.class, args);
	}
}
