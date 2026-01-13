package com.example.invoTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class InvoTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoTechApplication.class, args);
		System.out.println("Running");
	}

}

