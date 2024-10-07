package com.example.demo;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		Car car = Car.builder()
				.build();

		System.out.println(car);

		SpringApplication.run(DemoApplication.class, args);
	}
}

@Data
@Builder
class Car {
	private int power;
	private int weight;
	private int height;
	private int length;

	@Builder.Default
	private String color = "black";
	@Builder.Default
	private int numberOfWheel = 4;
}