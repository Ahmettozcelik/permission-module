package com.example.permissionmodule.service;

import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.entity.Permission;
import com.example.permissionmodule.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public void save(Permission newPermission) {
        permissionRepository.save(newPermission);

    }

    public Permission findPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    public Permission findPermissionByEmployeeId(Long employeeId) {
        return permissionRepository.findByEmployeeId(employeeId);
    }
}
