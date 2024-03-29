package com.example.permissionmodule.repository;

import com.example.permissionmodule.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
