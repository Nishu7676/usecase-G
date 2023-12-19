package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.example.user.filter.JWTFilter;

@SpringBootApplication
public class UserMicroserviceApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
		
		
	}

}
