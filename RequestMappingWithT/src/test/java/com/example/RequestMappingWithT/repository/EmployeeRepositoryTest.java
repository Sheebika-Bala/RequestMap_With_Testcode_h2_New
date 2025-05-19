package com.example.RequestMappingWithT.repository;

import com.example.RequestMappingWithT.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager;

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setup() {
        emp1 = new Employee(null, "Sarah", "sarah@dcis.net", "QA");
        emp2 = new Employee(null, "Maria", "maria@gmail.com", "Engineering");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
    }

    @Test
    void testFindAll() {
        List<Employee> all = employeeRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testFindById() {
        Optional<Employee> found = employeeRepository.findById(emp1.getId());
        assertTrue(found.isPresent());
        assertEquals("Sarah", found.get().getName());
    }

    @Test
    void testSave() {
        Employee emp = new Employee(null, "Snega", "snega@dcis.net", "Support");
        Employee saved = employeeRepository.save(emp);
        assertNotNull(saved.getId());
        assertEquals("Snega", saved.getName());
    }

    @Test
    void testDelete() {
        employeeRepository.deleteById(emp2.getId());
        assertFalse(employeeRepository.findById(emp2.getId()).isPresent());
    }

    @Test
    void testNamedQuery_FindByEmailDomain() {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailDomain", Employee.class);
        query.setParameter("domain", "%dcis.net");
        List<Employee> results = query.getResultList();
        assertEquals(1, results.size());
        assertEquals("sarah@dcis.net", results.get(0).getEmail());
    }
}
