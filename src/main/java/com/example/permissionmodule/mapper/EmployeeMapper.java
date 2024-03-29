package com.example.permissionmodule.mapper;

import com.example.permissionmodule.dto.EmployeeDto;
import com.example.permissionmodule.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDto employeeToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFullName(employee.getFullName());
        employeeDto.setStartOfWork(employee.getStartOfWork());
        employeeDto.setLanguage(employee.getLanguage());
        return employeeDto;
    }

    public Employee dtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setFullName(employeeDto.getFullName());
        employee.setStartOfWork(employeeDto.getStartOfWork());
        employee.setLanguage(employeeDto.getLanguage());
        return employee;
    }
}