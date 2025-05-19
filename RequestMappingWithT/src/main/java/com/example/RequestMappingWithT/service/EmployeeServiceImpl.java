package com.example.RequestMappingWithT.service;

import com.example.RequestMappingWithT.entity.Employee;
import com.example.RequestMappingWithT.exception.EmployeeNotFoundException;
import com.example.RequestMappingWithT.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getEmployeesByEmailDomain(){
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailDomain", Employee.class);
        query.setParameter("domain", "dcis.net");
        return query.getResultList();
    }

    @Override
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee details){
        Employee emp=employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        emp.setName(details.getName());
        emp.setEmail(details.getEmail());
        emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

        @Override
        public Employee patchEmployee(Long id, Employee details){
        Employee emp=employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        if (details.getName() !=null)emp.setName(details.getName());
        if (details.getEmail() !=null)emp.setEmail(details.getEmail());
        if (details.getDepartment() !=null)emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
        }

        @Override
        public String deleteEmployee(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
        return "Deleted employee with Id: " +id;
        }
}

