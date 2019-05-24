package com.mainul35.example.restControllers;

import com.mainul35.example.model.EmployeeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mainul35.example.model.Employee;
import com.mainul35.example.repositories.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable("id") final String empId) {
        return employeeRepository.findById(empId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> addEmployee(@RequestBody Employee employee){
        return employeeRepository.insert(employee).then();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EmployeeEvent> getEvents(@PathVariable("id") final String empId) {
        return employeeRepository.findById(empId)
                .flatMapMany(employee -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                    Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(
                            Stream.generate(() -> new EmployeeEvent(employee, new Date()))
                    );
                    return Flux.zip(interval, employeeEventFlux).map(Tuple2::getT2);
                });
    }

}
