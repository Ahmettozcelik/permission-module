package com.example.permissionmodule.controller;

import com.example.permissionmodule.dto.PermissionRequestDto;
import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.entity.PermissionRequest;
import com.example.permissionmodule.service.PermissionRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission/requests")
public class PermissionRequestController {

    private final PermissionRequestService permissionRequestService;

    public PermissionRequestController(PermissionRequestService permissionRequestService) {
        this.permissionRequestService = permissionRequestService;
    }

    @GetMapping
    public List<PermissionRequest> findAllPermissionRequests(){
        return permissionRequestService.findAll();
    }

    @GetMapping("{id}")
    public PermissionRequest findById(@PathVariable Long id){
        return permissionRequestService.findById(id);
    }

    @PostMapping
    public void createPermissionRequest(@RequestBody PermissionRequestDto permissionRequestDto){
        permissionRequestService.save(permissionRequestDto);
    }

}
