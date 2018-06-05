package com.mainul35.example;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RestController
public class EmployeeController {

    List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void init() throws InterruptedException {
        employees.add(new Employee("Syed Mainul", "Hasan", "Dhaka"));
        Thread.sleep(200);
        employees.add(new Employee("Umme Noorjahan ", "Popy", "Comilla"));
        Thread.sleep(200);
        employees.add(new Employee("Nurul Ferdous ", "Sunny", "USA"));
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return this.employees;
    }

    @PostMapping("/employees/add")
    public List<Employee> findBy(@RequestBody Employee employee){
        employee.setId(System.currentTimeMillis());
        this.employees.add(employee);
        return this.employees;
    }
}
