package com.mainul35.chainservice.repositories.sqlRepositories;

import org.springframework.data.repository.CrudRepository;

import com.mainul35.chainservice.model.domain.sqlDomains.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}