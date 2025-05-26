package com.fullstack.controller;

import com.fullstack.entity.Employee;
import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Tag(name = "HRM Application", description = "APIS Of Employee Controller")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Operation(summary = "Save Employee Data", description = "Api for generate new save Employee Data",
            responses = {
                    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Employee.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class))),
                    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(example = "Unauthorized. Please login and provide token in header")))})
    @PostMapping("/save")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee ID Does Not Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());

        return ResponseEntity.ok(employeeService.update(employee1));
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId) {
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }


}
