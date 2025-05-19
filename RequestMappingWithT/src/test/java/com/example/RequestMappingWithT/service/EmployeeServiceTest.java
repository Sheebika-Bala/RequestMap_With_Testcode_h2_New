package com.example.RequestMappingWithT.service;

import com.example.RequestMappingWithT.entity.Employee;
import com.example.RequestMappingWithT.exception.EmployeeNotFoundException;
import com.example.RequestMappingWithT.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee emp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emp = new Employee(1L, "Sheebika", "sheebika@dcis.net", "Engineering");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(emp));
        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(1, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    void testGetEmployeeById_Found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        Employee result = employeeService.getEmployeeById(1L);
        assertNotNull(result);
        assertEquals("Sheebika", result.getName());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(2L));
    }

    @Test
    void testCreateEmployee() {
        when(employeeRepository.save(emp)).thenReturn(emp);
        Employee result = employeeService.createEmployee(emp);
        assertEquals("Sheebika", result.getName());
    }

    @Test
    void testUpdateEmployee() {
        Employee updated = new Employee(null, "Nikitha", "nikitha@dcis.net", "Engineering");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        when(employeeRepository.save(any())).thenReturn(updated);
        Employee result = employeeService.updateEmployee(1L, updated);
        assertEquals("Nikitha", result.getName());
        assertEquals("Engineering", result.getDepartment());
    }

    @Test
    void testPatchEmployee() {
        Employee patch = new Employee(null, null, "nikitha@dcis.net", null);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        when(employeeRepository.save(any())).thenReturn(emp);
        Employee result = employeeService.patchEmployee(1L, patch);
        assertEquals("nikitha@dcis.net", result.getEmail());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        String message = employeeService.deleteEmployee(1L);
        verify(employeeRepository).deleteById(1L);
        assertEquals("Deleted employee with Id: 1", message);
    }
}
