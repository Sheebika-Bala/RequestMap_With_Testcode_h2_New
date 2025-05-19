package com.example.RequestMappingWithT.controller;

import com.example.RequestMappingWithT.Controller.EmployeeController;
import com.example.RequestMappingWithT.entity.Employee;
import com.example.RequestMappingWithT.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;

@ActiveProfiles("test")
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;
    private Employee emp;

    @BeforeEach
    void setup() {
        emp = new Employee(1L, "Sheebika", "sheebika@dcis.net", "Engineering");
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp));
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sheebika"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(emp);
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("sheebika@dcis.net"));
    }

    @Test
    void testGetEmployeesByEmailDomain() throws Exception {
        Mockito.when(employeeService.getEmployeesByEmailDomain()).thenReturn(Arrays.asList(emp));
        mockMvc.perform(get("/employees/by-domain"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("sheebika@dcis.net"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Mockito.when(employeeService.createEmployee(any(Employee.class))).thenReturn(emp);
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sheebika"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Mockito.when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(emp);
        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sheebika"));
    }

    @Test
    void testPatchEmployee() throws Exception {
        Mockito.when(employeeService.patchEmployee(eq(1L), any(Employee.class))).thenReturn(emp);
        mockMvc.perform(patch("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emp)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sheebika"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1L)).thenReturn("Deleted employee with Id: 1");
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted employee with Id: 1"));
    }

}


