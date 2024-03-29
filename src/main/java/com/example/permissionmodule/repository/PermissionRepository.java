package com.example.permissionmodule.repository;

import com.example.permissionmodule.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission,Long> {
    void deleteByEmployeeId(Long employeeId);

    public Permission findByEmployeeId(Long employeeId);
}
