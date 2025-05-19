package com.example.RequestMappingWithT.Controller;

import com.example.RequestMappingWithT.entity.Employee;
import com.example.RequestMappingWithT.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/by-domain")
    public List<Employee> getEmployeesByEmailDomain(){
        return employeeService.getEmployeesByEmailDomain();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee details){
        return employeeService.updateEmployee(id,details);
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable Long id, @RequestBody Employee details){
        return employeeService.patchEmployee(id,details);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}










/*
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/by-domain")
    public List<Employee> getEmployeesByEmailDomain() {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailDomain", Employee.class);
        return query.getResultList();
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee details) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        emp.setName(details.getName());
        emp.setEmail(details.getEmail());
        emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable Long id, @RequestBody Employee details) {
        Employee emp = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        if (details.getName() != null) emp.setName(details.getName());
        if (details.getEmail() != null) emp.setEmail(details.getEmail());
        if (details.getDepartment() != null) emp.setDepartment(details.getDepartment());
        return employeeRepository.save(emp);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.deleteById(id);
        return "Deleted employee with ID: " + id;
    }
}

 */



