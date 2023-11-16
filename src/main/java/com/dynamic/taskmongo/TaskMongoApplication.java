package com.dynamic.taskmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TaskMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskMongoApplication.class, args);
	}

}
