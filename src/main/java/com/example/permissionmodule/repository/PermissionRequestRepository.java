package com.example.permissionmodule.repository;

import com.example.permissionmodule.entity.PermissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRequestRepository extends JpaRepository<PermissionRequest,Long> {
    void deleteByEmployeeId(Long id);
}
