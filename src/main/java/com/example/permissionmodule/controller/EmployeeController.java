package com.example.permissionmodule.controller;

import com.example.permissionmodule.dto.EmployeeDto;
import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAllEmployees(){
        return employeeService.findAll();
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeDto newEmployeeDto){
        employeeService.save(newEmployeeDto);
    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> findEmployeeById(@PathVariable Long employeeId){
        return employeeService.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,@RequestBody Employee newEmployee){
        return employeeService.updateEmployee(employeeId,newEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.deleteById(employeeId);
    }
}
