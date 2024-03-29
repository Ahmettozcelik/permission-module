package com.example.permissionmodule.controller;

import com.example.permissionmodule.entity.Permission;
import com.example.permissionmodule.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public List<Permission> findAllPermissions(){
        return permissionService.findAll();
    }


    @GetMapping("/{permissionId}")
    public Permission findPermissionById(@PathVariable Long permissionId){
        return permissionService.findPermissionById(permissionId);
    }

}
