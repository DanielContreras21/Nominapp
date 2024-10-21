package com.nominapp.employee.service;

import com.nominapp.employee.entity.Employee;
import com.nominapp.employee.event.EmployeeCreatedEvent;
import com.nominapp.employee.exception.NotFoundException;
import com.nominapp.employee.external.services.PaysheetService;
import com.nominapp.employee.repository.EmployeeRepository;
import com.nominapp.employee.service.abstraction.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private KafkaTemplate<String, EmployeeCreatedEvent> kafkaTemplate;

    private Logger logger = LoggerFactory.getLogger(PaysheetService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Employee employeeSaved = repository.save(employee);

        kafkaTemplate.send("notificationTopic", new EmployeeCreatedEvent(employee.getId()));

        return employeeSaved;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Employee employee = repository.findById(id).orElseThrow( () -> new NotFoundException("Employee with id: " + id + " was not found"));

        ArrayList paysheets = restTemplate.getForObject("http://Paysheet/paysheets/employee/" + employee.getId(), ArrayList.class);
        logger.info("{}", paysheets);

        employee.setPaysheets(paysheets);
        return employee;
    }
}
