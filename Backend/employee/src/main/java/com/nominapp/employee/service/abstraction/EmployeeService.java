package com.nominapp.employee.service.abstraction;

import com.nominapp.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployee(Long id);
}
