package com.mainul35.springbootjaxrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {JerseyConfig.class})
public class SpringBootJaxrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJaxrsApplication.class, args);
	}

}
