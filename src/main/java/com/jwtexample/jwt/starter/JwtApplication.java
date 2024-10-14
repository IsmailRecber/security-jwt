package com.jwtexample.jwt.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {"com.jwtexample.jwt"})
@EnableJpaRepositories(basePackages = {"com.jwtexample.jwt"})
@ComponentScan(basePackages = {"com.jwtexample.jwt"})
@ServletComponentScan(basePackages = {"com.jwtexample.jwt"})
@ConfigurationPropertiesScan(basePackages = {"com.jwtexample.jwt"})
@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
