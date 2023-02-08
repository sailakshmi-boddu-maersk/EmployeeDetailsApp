package com.slb.EmployeeDetailsApp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeDetailsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsAppApplication.class, args);
		System.out.println("my employee details app");
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
