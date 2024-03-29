package com.example.permissionmodule.mapper;

import com.example.permissionmodule.dto.PermissionDto;
import com.example.permissionmodule.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionDto permissionToDto (Permission permission) {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setHasAnnualLeave(permission.getHasAnnualLeave());

        return permissionDto;
    }

    public Permission dtoToPermission(PermissionDto permissionDto) {
        Permission permission = new Permission();
        permission.setHasAnnualLeave(permissionDto.getHasAnnualLeave());

        return permission;
    }
}
