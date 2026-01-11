package com.example.invoTech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class InvoTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoTechApplication.class, args);
		System.out.println("Running");
	}

}

