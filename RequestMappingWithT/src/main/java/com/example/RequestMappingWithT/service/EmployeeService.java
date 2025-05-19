package com.example.RequestMappingWithT.service;

import  com.example.RequestMappingWithT.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    List<Employee> getEmployeesByEmailDomain();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee details);
    Employee patchEmployee(Long id,Employee details);
    String deleteEmployee(Long id);
}


















