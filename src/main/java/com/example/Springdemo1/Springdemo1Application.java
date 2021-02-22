package com.example.Springdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Springdemo1Application {

	public static void main(String[] args) {
		System.out.println("Hello World!!");
		SpringApplication.run(Springdemo1Application.class, args);
	}

}
