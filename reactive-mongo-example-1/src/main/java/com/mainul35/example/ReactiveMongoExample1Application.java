package com.mainul35.example;

import com.mainul35.example.model.Employee;
import com.mainul35.example.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;
import java.util.stream.Stream;

@ComponentScan(basePackages = {"com.mainul35.example.restControllers", "com.mainul35.example.repositories"})
@SpringBootApplication
public class ReactiveMongoExample1Application {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoExample1Application.class, args);
	}

	@Bean
	CommandLineRunner employees(EmployeeRepository employeeRepository){
		return args -> {
			employeeRepository.deleteAll().subscribe(null, null, ()->{
				Stream.of(new Employee(UUID.randomUUID().toString(), "Peter", 23000L),
						new Employee(UUID.randomUUID().toString(), "John", 23000L),
				new Employee(UUID.randomUUID().toString(), "Mike", 23000L))
				.forEach(employee -> {
					employeeRepository.save(employee).subscribe(System.out::println);
				});
			});
		};
	}
}
