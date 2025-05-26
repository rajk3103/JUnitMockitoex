package com.fullstack.service;

import com.fullstack.entity.Employee;
import com.fullstack.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @MockitoBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveTest() {

        Employee employee = new Employee(121, "SWARA", "PUNE", 79000.99, 9898998989898L);

        employeeService.save(employee);

        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);

    }

    @Test
    public void findAllTest() {

        Mockito.when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(121, "SWARA", "PUNE", 79000.99, 9898998989898L),
                new Employee(122, "POOJA", "PUNE", 89000.99, 9811998989898L),
                new Employee(123, "RUTUJA", "PUNE", 59000.99, 9558998989898L)).toList());

        Assertions.assertEquals(3, employeeService.findAll().stream().collect(Collectors.counting()));

    }

    @Test
    public void findByIdTest() {
        Mockito.when(employeeRepository.findById(121)).thenReturn(Optional.of(new Employee(121, "SWARA", "PUNE", 79000.99, 9898998989898L)));

        employeeService.findById(121);

        Mockito.verify(employeeRepository, Mockito.times(1)).findById(121);
    }

    @Test
    public void updateTest() {
        Employee employee = new Employee(121, "SWARA", "PUNE", 79000.99, 9898998989898L);
        employeeService.update(employee);

        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    public void deleteByIdTest() {
        Employee employee = new Employee(121, "SWARA", "PUNE", 79000.99, 9898998989898L);

        employeeService.deleteById(employee.getEmpId());

        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(employee.getEmpId());
    }

}
