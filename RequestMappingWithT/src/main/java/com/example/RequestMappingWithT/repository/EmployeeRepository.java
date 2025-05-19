package com.example.RequestMappingWithT.repository;

import com.example.RequestMappingWithT.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
