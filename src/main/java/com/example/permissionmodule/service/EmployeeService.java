package com.example.permissionmodule.service;

import com.example.permissionmodule.dto.EmployeeDto;
import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.entity.Permission;
import com.example.permissionmodule.language.LanguageConfig;
import com.example.permissionmodule.mapper.EmployeeMapper;
import com.example.permissionmodule.repository.EmployeeRepository;
import com.example.permissionmodule.repository.PermissionRepository;
import com.example.permissionmodule.repository.PermissionRequestRepository;
import com.example.permissionmodule.repository.PermissionResponseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionRequestRepository permissionRequestRepository;
    private final PermissionResponseRepository permissionResponseRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, PermissionRepository permissionRepository, PermissionRequestRepository permissionRequestRepository, PermissionResponseRepository permissionResponseRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.permissionRepository = permissionRepository;
        this.permissionRequestRepository = permissionRequestRepository;
        this.permissionResponseRepository = permissionResponseRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void save(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.dtoToEmployee(employeeDto);
        employeeRepository.save(employee);

        checkWorkTime(employee);
    }

    public Optional<Employee> findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee updateEmployee(Long employeeId, Employee newEmployee) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            Employee foundEmployee = employee.get();
            foundEmployee.setFullName(newEmployee.getFullName());
            foundEmployee.setStartOfWork(newEmployee.getStartOfWork());
            foundEmployee.setLanguage(newEmployee.getLanguage());
            employeeRepository.save(foundEmployee);
            return foundEmployee;
        } else
            throw new NotFoundException(LanguageConfig.getErrorMessage("employee.not.found",Locale.US.getLanguage()));
    }

    @Transactional
    public void deleteById(Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            permissionResponseRepository.deleteById(employeeId);
            permissionRequestRepository.deleteByEmployeeId(employeeId);
            permissionRepository.deleteByEmployeeId(employeeId);
            employeeRepository.deleteById(employeeId);
        }
        else
            throw new NotFoundException(LanguageConfig.getErrorMessage("employee.not.found",Locale.US.getLanguage()));
    }

    public long getWorkTime(Employee employee){
        LocalDate now = LocalDate.now();

        return ChronoUnit.DAYS.between(employee.getStartOfWork(), now);
    }

    public void checkWorkTime(Employee employee) {

        Permission permission = new Permission();
        permission.setEmployeeId(employee.getId());

        long workTime = getWorkTime(employee);

        int annualLeave = 0;
        int intWorkTime = (int) workTime;
        for(int i = intWorkTime ; i > 0 ; i-=365) {
            if( i > 3650){
                annualLeave += 24;
            }
            else if( i > 1825){
                annualLeave += 18;
            }
            else if( i > 365){
                annualLeave += 15;
            }
        }
        permission.setHasAnnualLeave(annualLeave);

        permissionRepository.save(permission);

    }

}
