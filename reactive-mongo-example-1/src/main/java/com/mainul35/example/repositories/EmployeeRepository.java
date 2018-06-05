package com.mainul35.example.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mainul35.example.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>{

	Flux<Employee> findAll();

}
