package com.nominapp.employee.controller;

import com.nominapp.employee.entity.Employee;
import com.nominapp.employee.service.abstraction.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public ResponseEntity<Employee> createUser(@RequestBody Employee user){
        return ResponseEntity.ok(service.saveEmployee(user));
    }

    @GetMapping("/{id}")
//    @CircuitBreaker(name = "getEmployeeBreaker", fallbackMethod = "getEmployeeFallback")
    @Retry(name ="getEmployeeService", fallbackMethod = "getEmployeeFallback")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
        return ResponseEntity.ok(service.getEmployee(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers(){
        return ResponseEntity.ok(service.getAllEmployees());
    }


    public ResponseEntity<Employee> getEmployeeFallback(Long id, Exception exception){
        log.info("The backup is exceting because service is inactive", exception.getMessage());

        Employee employee = Employee.builder()
                .name("Hola")
                .secondName("Hola")
                .lastName("Hola")
                .secondLastName("Hola")
                .build();

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
